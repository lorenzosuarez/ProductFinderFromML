package com.example.productfinderfromml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        (this as AppCompatActivity).supportActionBar

        navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


        /*binding.items.apply {
            layoutManager = GridLayoutManager(context, 1)
        }*/
        //val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        // binding.items.addItemDecoration(decoration)
        //binding.items.adapter = mainPagingAdapter

        /*binding.items.adapter = adapter.withLoadStateHeaderAndFooter(
            header = ReposLoadStateAdapter { adapter.retry() },
            footer = ReposLoadStateAdapter { adapter.retry() }
        )*/

        //search("motorola")

        /*binding.searchView.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    search(v.text.toString()); true
                }
                else -> false
            }
            //search("motor dc rs 795 12 24v")
        }*/

    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }*/



}