package com.android.challenge.home.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductsRaw(
    @Json(name = "spotlight") val spotlight: List<SpotlightRaw>?,
    @Json(name = "products") val products: List<ProductRaw>?,
    @Json(name = "cash") val cash: CashRaw?
)