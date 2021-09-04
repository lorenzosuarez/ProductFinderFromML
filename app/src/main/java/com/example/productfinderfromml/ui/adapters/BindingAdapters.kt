package com.example.productfinderfromml.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.productfinderfromml.R
import com.example.productfinderfromml.application.AppConstants.SYMBOL
import com.example.productfinderfromml.data.model.item.AvailableSort
import com.example.productfinderfromml.data.model.item.Seller
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.data.model.detail.SellerAddress
import com.example.productfinderfromml.presentation.DetailViewModel
import com.example.productfinderfromml.utils.priceFormat
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip

@BindingAdapter("listDataSort")
fun bindRecyclerViewSort(recyclerView: RecyclerView, data: List<Pair<AvailableSort, Boolean>>?) {
    val adapter = recyclerView.adapter as SortAdapter
    adapter.submitList(data)
    recyclerView.scheduleLayoutAnimation()
}


@BindingAdapter("listData")
fun bindRecyclerView(viewpager: ViewPager2, data: List<Picture>?) {
    val adapter = viewpager.adapter as ViewPagerAdapter
    adapter.submitList(data)
    viewpager.scheduleLayoutAnimation()
}

@BindingAdapter("changeIcon")
fun setChangeIcon(materialButton: MaterialButton, status: DetailViewModel.Status?) {
    status?.let {
        val iconResource = when (status) {
            DetailViewModel.Status.STATUS1 -> R.drawable.ic_up
            DetailViewModel.Status.STATUS2 -> R.drawable.ic_down
        }

        with(materialButton) {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, iconResource, 0)
        }
    }
}

@BindingAdapter("setPlace")
fun setPlace(chip: Chip, sellerAddress: SellerAddress?) {
    sellerAddress?.let {
        //, ${it.state.name}
        chip.text = sellerAddress.city.name
        chip.visibility = if (it.city.name.isEmpty() && it.state.name.isEmpty()) View.GONE else View.VISIBLE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setPrice")
fun setPrice(textView: TextView, price: Double?) {
    price?.let {
        textView.text = """${SYMBOL}${price.priceFormat()}"""
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setOriginalPrice")
fun setOriginalPrice(textView: TextView, productDetail: ProductDetail?) {
    productDetail?.let {
        with(productDetail.body) {
            if (originalPrice > price && originalPrice > 0) {
                textView.text = """${SYMBOL}${originalPrice.priceFormat()}"""
                textView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }
}

@BindingAdapter("setTextCondition")
fun setTextCondition(textView: TextView, condition: String?) {
    val context = textView.context

    textView.text = when (condition) {
        "new" -> context.getString(R.string.new_)
        "used" -> context.getString(R.string.used)
        else -> ""
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setTextSellerNickname")
fun setTextSellerNickname(textView: TextView, seller: Seller?) {
    val context = textView.context
    if (seller != null) {
        if (seller.eshop?.nickName?.isNotEmpty() == true) {
            textView.text = "${context.getString(R.string.seller)} ${seller.eshop.nickName}"
        }
    }
}