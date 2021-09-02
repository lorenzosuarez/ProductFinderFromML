package com.example.productfinderfromml.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.databinding.ItemRowBinding
import com.example.productfinderfromml.utils.showIf
import java.text.DecimalFormat

class ResultadoAdapter(private val context: Context, private val onClickListener: OnClickListener) :
    PagingDataAdapter<Resultado, ResultadoAdapter.ItemsViewHolder>(
        ItemsDiffCallback()
    ) {


    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onClickListener, context)
    }

    class OnClickListener(val clickListener: (item: Resultado, image: ImageView) -> Unit) {
        fun onClick(item: Resultado, image: ImageView) = clickListener(item, image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {

        return ItemsViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ItemsViewHolder(
        private val binding: ItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Resultado?, onClickListener: OnClickListener, context: Context) {
            val dec = DecimalFormat("#,###.##")

            binding.let { item ->
                if (data != null) {
                    item.apply {
                        image.transitionName = data.id
                        title.text = if (data.title.trim().length > 100) "${data.title.trim().substring(0..80)}..." else data.title.trim()
                        price.text = "$${dec.format(data.price)}"
                        "${context.getString(R.string.seller_nickname)} : ${data.seller.eshop?.nickName}".also {
                            sellerNickname.text = it
                            sellerNickname.showIf { data.seller.eshop?.nickName.isNullOrEmpty().not() }
                        }
                        freeShipping.showIf { data.shipping.freeShipping }
                        image.load(data.thumbnail) {
                            listener(onSuccess = { _: ImageRequest, _: ImageResult.Metadata ->
                                binding.shimmer.hideShimmer()
                            })

                            placeholder(R.color.shimmer_placeholder)
                        }
                        card.setOnClickListener { onClickListener.onClick(data, item.image) }
                    }

                    /*Glide.with(context)
                        .load(data.thumbnail)
                        .centerCrop()
                        .into(it.image)*/
                }
            }


        }
    }

    private class ItemsDiffCallback : DiffUtil.ItemCallback<Resultado>() {
        override fun areItemsTheSame(oldItem: Resultado, newItem: Resultado): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Resultado, newItem: Resultado): Boolean {
            return oldItem == newItem
        }
    }

}