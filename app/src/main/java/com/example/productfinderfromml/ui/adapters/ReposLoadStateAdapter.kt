package com.example.productfinderfromml.ui.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.productfinderfromml.ui.ResultLoadStateViewHolder

class ReposLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ResultLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ResultLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ResultLoadStateViewHolder {
        return ResultLoadStateViewHolder.create(parent, retry)
    }
}