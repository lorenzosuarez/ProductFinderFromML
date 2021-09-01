package com.example.productfinderfromml.ui.details.viewpager

import android.content.Context
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.productfinderfromml.R

class CarouselTransformer(val context: Context) :
    ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageMarginPx = context.resources.getDimensionPixelOffset(R.dimen.text_size_large)
        val offsetPx = context.resources.getDimensionPixelOffset(R.dimen.text_size_large)

        page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        val offset = position * -(2 * offsetPx + pageMarginPx)
        page.translationX = offset
    }
}