/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.productfinderfromml.ui

/**
 * Adapter for the list of repositories.
 */
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.databinding.ItemRowBinding
import com.example.productfinderfromml.utils.showIf
import java.text.DecimalFormat

/**
 * Adapter for the list of repositories.
 */
class ResultadoAdapter(private val context: Context, private val onClickListener: OnClickListener) : PagingDataAdapter<Resultado, ResultadoAdapter.ItemsViewHolder>(
    ItemsDiffCallback()
) {


    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onClickListener, context)
    }

    class OnClickListener(val clickListener: (item: Resultado, image : ImageView) -> Unit) {
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
                    item.image.transitionName = data.id
                    item.title.text = if (data.title.trim().length > 100) "${data.title.trim().substring(0..80)}..." else data.title.trim()
                    item.price.text = "$${dec.format(data.price)}"
                    item.sellerNickname.text = data.seller.eshop?.nickName
                    item.freeShipping.showIf { data.shipping.freeShipping }
                    item.image.load(data.thumbnail) {
                        placeholder(R.drawable.ic_launcher_background)
                    }

                    binding.card.setOnClickListener {
                        onClickListener.onClick(data, item.image)
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