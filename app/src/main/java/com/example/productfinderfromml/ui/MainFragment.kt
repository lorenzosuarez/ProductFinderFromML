package com.example.productfinderfromml.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productfinderfromml.R
import com.example.productfinderfromml.databinding.FragmentMainBinding
import com.example.productfinderfromml.databinding.SortBottomSheetBinding
import com.example.productfinderfromml.presentation.MainViewModel
import com.example.productfinderfromml.ui.adapters.ReposLoadStateAdapter
import com.example.productfinderfromml.ui.adapters.ResultAdapter
import com.example.productfinderfromml.ui.adapters.SortAdapter
import com.example.productfinderfromml.ui.details.DetailsFragment
import com.example.productfinderfromml.utils.onQueryTextChanged
import com.example.productfinderfromml.utils.snack
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Lorenzo Suarez on 30/8/2021.
 */

@ExperimentalPagingApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainPagingAdapter: ResultAdapter
    private lateinit var bottomSheetBinding: SortBottomSheetBinding
    private lateinit var bottomSheetSort: BottomSheetDialog
    private lateinit var sort: String
    private val mViewModel by viewModels<MainViewModel>()
    private var searchJob: Job? = null
    private val TAG = MainFragment::class.java.simpleName

    companion object {
        private const val START_POSITION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        bottomSheetSort = BottomSheetDialog(requireContext())

        binding.apply {
            lifecycleOwner = this@MainFragment
            viewModel = mViewModel
        }

        fragmentBindEvents()

        bottomSheetBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.sort_bottom_sheet,
            null,
            false
        )
        bottomSheetSort.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.apply {
            viewModel = mViewModel
        }

        bottomSheetBinding.sortButton.setOnClickListener {
            if(binding.searchView.query.toString().isNotEmpty())
                search(binding.searchView.query.toString(), sort)
            else
                binding.root.snack(getString(R.string.input_query))

            bottomSheetSort.cancel()
        }

        bottomSheetBinding.sortList.adapter = SortAdapter(
            SortAdapter.OnClickListener { sort ->
                this.sort = sort
            })

        mainPagingAdapter = ResultAdapter(context = requireContext(), ResultAdapter.OnClickListener { item, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to item.id
            )
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(item = item)
            findNavController().navigate(action, extras)
        })

        initAdapter()


    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.myGalacticLeague -> {
                bottomSheetSort.show()
                false
            }
            else -> false
        }
    }

    override fun onResume() {
        super.onResume()
        if (binding.searchView.query.toString().isNotEmpty())
            search(binding.searchView.query.toString(), null)
    }

    private fun search(query: String?, sort: String?) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            mViewModel.searchRepo(query, pSortName = sort).collectLatest {
                mainPagingAdapter.submitData(it)
            }
        }
    }

    private fun fragmentBindEvents() {
        with(binding) {
            items.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    fab.isVisible = dy < START_POSITION
                    val scrollPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    items.isEnabled = scrollPosition == START_POSITION
                }
            })

            fab.setOnClickListener {
                items.smoothScrollToPosition(START_POSITION)
            }

            searchView.onQueryTextChanged { value ->
                search(value, null)
            }
        }
    }

    private fun initAdapter() {
        binding.items.adapter = mainPagingAdapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { mainPagingAdapter.retry() },
            footer = ReposLoadStateAdapter { mainPagingAdapter.retry() }
        )

        mainPagingAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty = loadState.refresh is LoadState.NotLoading && mainPagingAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding.items.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error

            errorState?.let {
                binding.container.snack("Error ${it.error}")
                Log.e(TAG, "LoadStateError : ${it.error}")
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.items.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.items.visibility = View.VISIBLE
        }
    }
}