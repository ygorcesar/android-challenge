package com.android.challenge.base.extensions.view

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

var View?.isVisible: Boolean
    get() = this?.visibility == View.VISIBLE
    set(isVisible) {
        this?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

fun View.getString(@StringRes stringId: Int): String = this.context.getString(stringId)

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this)
}

fun View.getDimen(@DimenRes dimenResId: Int) = resources.getDimension(dimenResId).toInt()