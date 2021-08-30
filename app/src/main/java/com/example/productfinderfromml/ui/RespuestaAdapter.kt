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
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.databinding.TragosRowBinding

/**
 * Adapter for the list of repositories.
 */
class RespuestaAdapter(private val context: Context) : PagingDataAdapter<Resultado, RespuestaAdapter.PlayersViewHolder>(
PlayersDiffCallback()
) {


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {

        val data = getItem(position)

        holder.bind(data)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {

        return PlayersViewHolder(
            TragosRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class PlayersViewHolder(
        private val binding: TragosRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Resultado?) {

            binding.let {
                if (data != null) {
                    it.txtTitulo.text = data.title

                    Glide.with(context)
                        .load(data.thumbnail)
                        .centerCrop()
                        .into(it.imgCocktail)
                }
            }

        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<Resultado>() {
        override fun areItemsTheSame(oldItem: Resultado, newItem: Resultado): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Resultado, newItem: Resultado): Boolean {
            return oldItem == newItem
        }
    }

}