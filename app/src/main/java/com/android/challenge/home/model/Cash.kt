package com.android.challenge.home.model

data class Cash(
    val title: String,
    val bannerURL: String,
    val description: String
) {

    val isValid: Boolean get() =  title.isNotBlank() && description.isNotBlank()
}