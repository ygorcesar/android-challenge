package com.android.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductRaw(
    @Json(name = "name") val name: String?,
    @Json(name = "imageURL") val imageURL: String?,
    @Json(name = "description") val description: String?
)