package com.android.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpotlightRaw(
    @Json(name = "name") val name: String?,
    @Json(name = "bannerURL") val bannerURL: String?,
    @Json(name = "description") val description: String?
)