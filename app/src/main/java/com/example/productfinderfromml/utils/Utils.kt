package com.example.productfinderfromml.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import androidx.transition.Transition
import java.text.DecimalFormat


inline fun SearchView.onQueryTextChanged(crossinline onQueryTextChanged: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextChanged(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    if (condition(this)) {
        show()
    } else {
        hide()
    }
}

inline fun <T : View> T.hideIf(condition: (T) -> Boolean) {
    if (condition(this)) {
        hide()
    } else {
        show()
    }
}

fun View.slideUp(viewGroup: ViewGroup, duration: Long = 600) {
    val transition: Transition = Slide(Gravity.TOP)
    transition.duration = duration
    transition.addTarget(this.id)
    androidx.transition.TransitionManager.beginDelayedTransition(viewGroup, transition)
    this.visibility = View.GONE
}

fun View.slideDown(viewGroup: ViewGroup, duration: Long = 600) {
    val transition: Transition = Slide(Gravity.TOP)
    transition.duration = duration
    transition.addTarget(this.id)
    androidx.transition.TransitionManager.beginDelayedTransition(viewGroup, transition)
    this.visibility = View.VISIBLE
}

fun Double.priceFormat(): String = DecimalFormat("#,###.##").format(this)

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}