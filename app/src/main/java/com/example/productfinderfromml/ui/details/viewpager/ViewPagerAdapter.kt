package com.example.productfinderfromml.ui.details.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.databinding.ItemPageBinding

class ViewPagerAdapter(
    private val pictures: List<Picture>
) : RecyclerView.Adapter<PagerVH>() {

    //array of colors to change the background color of screen

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH = PagerVH(
        ItemPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    //get the size of color array
    override fun getItemCount(): Int = pictures.size

    //binding the screen with view
    /*override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        container.setBackgroundResource(colors[position])
    }

    override fun onBindViewHolder(holder: ResultadoAdapter.ItemsViewHolder, position: Int) {
        holder.bind(items[position])
    }*/

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.bind(pictures[position])
    }
}

class PagerVH(
    private val binding: ItemPageBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(picture: Picture) {
        binding.image.load(picture.url) { placeholder(R.drawable.ic_launcher_background) }
    }
}
//class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)