package com.example.productfinderfromml.ui

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.ui.details.viewpager.ViewPagerAdapter

@BindingAdapter("listData")
fun bindRecyclerView(viewpager: ViewPager2, data: List<Picture>?) {
    val adapter = viewpager.adapter as ViewPagerAdapter
    adapter.submitList(data)
    viewpager.scheduleLayoutAnimation()
}
