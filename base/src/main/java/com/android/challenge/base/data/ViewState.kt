package com.android.challenge.base.data

sealed class ViewState {
    object Loading : ViewState()
    data class Complete<T>(val response: T) : ViewState()
    data class Failed(val error: Throwable? = null) : ViewState()
}