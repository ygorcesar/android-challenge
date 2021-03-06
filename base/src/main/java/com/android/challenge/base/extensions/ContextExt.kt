package com.android.challenge.base.extensions

import android.content.Context
import android.content.res.TypedArray
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat

/**
 * Extension property to get a [NetworkInfo]
 *
 * @return The active network info in the Android Framework
 */
val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun Context.getColorRes(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)