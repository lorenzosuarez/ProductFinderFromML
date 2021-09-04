package com.example.productfinderfromml.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productfinderfromml.data.model.item.AvailableSort
import com.example.productfinderfromml.databinding.ItemSortBinding

class SortAdapter(private val onClickListener: OnClickListener) : ListAdapter<Pair<AvailableSort, Boolean>, SortAdapter.SortVH>(DiffCallback) {
    private var mSelectedItem : Int? = null

    class OnClickListener(val clickListener: (sort: String) -> Unit) {
        fun onClick(sort: String) = clickListener(sort)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortVH = SortVH(
        ItemSortBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: SortVH, position: Int) {
        val picture = getItem(position)
        holder.bind(picture, position, mSelectedItem) { selected, sort ->
            mSelectedItem = selected
            onClickListener.onClick(sort)
            notifyDataSetChanged()
        }
    }

    class SortVH(
        private val binding: ItemSortBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentPair: Pair<AvailableSort, Boolean>, position: Int, selectedPosition: Int?, callBack: (Int, String) -> Unit) {
            binding.sortName.text = currentPair.first.name

            when (selectedPosition) {
                null -> binding.sortRadio.isChecked = currentPair.second
                else -> binding.sortRadio.isChecked = selectedPosition == position
            }

            binding.card.setOnClickListener {
                callBack(bindingAdapterPosition,  currentPair.first.id)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pair<AvailableSort, Boolean>>() {

        override fun areItemsTheSame(oldItem: Pair<AvailableSort, Boolean>, newItem: Pair<AvailableSort, Boolean>): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Pair<AvailableSort, Boolean>, newItem: Pair<AvailableSort, Boolean>): Boolean =
            oldItem.first.id == newItem.first.id
    }
}