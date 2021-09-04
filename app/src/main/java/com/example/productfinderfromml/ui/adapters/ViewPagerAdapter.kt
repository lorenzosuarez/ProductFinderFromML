package com.example.productfinderfromml.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.databinding.ItemPageBinding

class ViewPagerAdapter : ListAdapter<Picture, ViewPagerAdapter.PagerVH>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH = PagerVH(
        ItemPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        val picture = getItem(position)
        holder.bind(picture)
    }

    class PagerVH(
        private val binding: ItemPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            binding.image.load(picture.url) {
                /*listener( onSuccess = { _: ImageRequest, _: ImageResult.Metadata ->
                    binding.shimmer.hideShimmer()
                })*/

                placeholder( R.color.shimmer_placeholder) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Picture>() {

        override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
            oldItem.url == newItem.url
    }
}