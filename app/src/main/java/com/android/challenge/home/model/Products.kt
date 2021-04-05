package com.android.challenge.home.model

data class Products(
    val spotlight: List<Spotlight>,
    val products: List<Product>,
    val cash: Cash
)