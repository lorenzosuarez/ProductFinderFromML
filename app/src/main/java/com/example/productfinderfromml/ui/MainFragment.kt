package com.example.productfinderfromml.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.flatMap
import com.example.productfinderfromml.R
import com.example.productfinderfromml.databinding.FragmentMainBinding
import com.example.productfinderfromml.databinding.SortBottomSheetBinding
import com.example.productfinderfromml.presentation.MainViewModel
import com.example.productfinderfromml.utils.onQueryTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * Created by Lorenzo Suarez on 3/5/2021.
 */

@ExperimentalPagingApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainPagingAdapter: ResultAdapter
    private lateinit var sortBottomSheetBinding: SortBottomSheetBinding
    private lateinit var bottomSheetSort: BottomSheetDialog
    private lateinit var sort: String

    private val viewModel by viewModels<MainViewModel>()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    private fun search(query: String?, sort: String?) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query, pSortName = sort).collectLatest {
                mainPagingAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        bottomSheetSort = BottomSheetDialog(requireContext())

        sortBottomSheetBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.sort_bottom_sheet,
            null,
            false
        )

        sortBottomSheetBinding.sortButton.setOnClickListener {
            search(binding.searchView.query.toString(), sort)
            bottomSheetSort.cancel()
        }

        sortBottomSheetBinding.viewModel = viewModel
        sortBottomSheetBinding.filterList.adapter = SortAdapter(
            SortAdapter.OnClickListener { sort ->
                this.sort = sort
            })

        bottomSheetSort.setContentView(sortBottomSheetBinding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mainPagingAdapter = ResultAdapter(context = requireContext(), ResultAdapter.OnClickListener { item, imageView ->
            val extras = FragmentNavigatorExtras(
                imageView to item.id
            )
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(item = item)
            findNavController().navigate(action, extras)
        })

        binding.items.adapter = mainPagingAdapter

        binding.searchView.onQueryTextChanged { value ->
            search(value, null)
        }
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
        search(binding.searchView.query.toString(), null)
    }
}