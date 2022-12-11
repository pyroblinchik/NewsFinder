package com.pyroblinchik.newsfinder.util.view

import android.view.View
import android.view.ViewGroup

fun View?.gone() {
    this?.visibility = View.GONE
}

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}
fun View.isGone(): Boolean {
    return visibility == View.GONE
}
fun View.toggleVisibility(isShow: Boolean) {
    if (isShow) visible() else gone()
}
fun View.toggleInvisibility(isShow: Boolean) {
    if (isShow) visible() else invisible()
}
