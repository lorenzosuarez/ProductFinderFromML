package com.example.productfinderfromml.ui

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.productfinderfromml.R
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.presentation.DetailViewModel
import com.example.productfinderfromml.ui.details.viewpager.ViewPagerAdapter
import com.google.android.material.button.MaterialButton

@BindingAdapter("listData")
fun bindRecyclerView(viewpager: ViewPager2, data: List<Picture>?) {
    val adapter = viewpager.adapter as ViewPagerAdapter
    adapter.submitList(data)
    viewpager.scheduleLayoutAnimation()
}

@BindingAdapter("changeIcon")
fun setChangeIcon(materialButton: MaterialButton ,status: DetailViewModel.Status?) {
    status?.let {
        val iconResource = when(status) {
            DetailViewModel.Status.STATUS1 -> R.drawable.ic_up
            DetailViewModel.Status.STATUS2 -> R.drawable.ic_down
        }

        materialButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResource, 0)
    }
}
