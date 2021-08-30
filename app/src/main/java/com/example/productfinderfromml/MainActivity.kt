package com.example.productfinderfromml

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.productfinderfromml.databinding.ActivityMainBinding
import com.example.productfinderfromml.presentation.MainViewModel
import com.example.productfinderfromml.ui.ReposLoadStateAdapter
import com.example.productfinderfromml.ui.ResultadoAdapter
import com.example.productfinderfromml.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity(){
    private val viewModel by viewModels<MainViewModel>()
    lateinit var mainPagingAdapter: ResultadoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /****** NUEVO PAGIN ****/
        mainPagingAdapter = ResultadoAdapter(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rvTragos.addItemDecoration(decoration)
        binding.rvTragos.adapter = mainPagingAdapter

        /*binding.rvTragos.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )*/


        binding.searchView.onQueryTextChanged {
            search(it)
            //search("motor dc rs 795 12 24v")
        }

    }

    private var searchJob: Job? = null

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                mainPagingAdapter.submitData(lifecycle, it)
            }
        }
    }
}