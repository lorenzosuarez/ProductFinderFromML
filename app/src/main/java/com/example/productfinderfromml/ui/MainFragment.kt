package com.example.productfinderfromml.ui

import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.example.productfinderfromml.R
import com.example.productfinderfromml.databinding.FragmentMainBinding
import com.example.productfinderfromml.presentation.MainViewModel
import com.example.productfinderfromml.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


/**
 * Created by Lorenzo Suarez on 3/5/2021.
 */

@ExperimentalPagingApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var mainPagingAdapter: ResultadoAdapter
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                mainPagingAdapter.submitData(lifecycle, it)
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
        binding.lifecycleOwner = this

        mainPagingAdapter = ResultadoAdapter(context = requireContext())
        binding.items.adapter = mainPagingAdapter

        search("motorola")

        binding.searchView.onQueryTextChanged {
            search(it)
        }
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.myGalacticLeague -> {
                false
            }
            else -> false
        }
    }


}