package com.android.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CashRaw(
    @Json(name = "title") val title: String?,
    @Json(name = "bannerURL") val bannerURL: String?,
    @Json(name = "description") val description: String?
)