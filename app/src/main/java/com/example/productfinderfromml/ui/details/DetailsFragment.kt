package com.example.productfinderfromml.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import com.example.productfinderfromml.R
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Lorenzo Suarez on 3/5/2021.
 */

@ExperimentalPagingApi
@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        requireArguments().let {
            //DetailsFragmentArgs.fromBundle(it).also { args ->
            //itemReceived = args.item
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding = FragmentDetailsBinding.bind(view)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.myGalacticLeague).isVisible = false

        super.onPrepareOptionsMenu(menu)
    }
}

