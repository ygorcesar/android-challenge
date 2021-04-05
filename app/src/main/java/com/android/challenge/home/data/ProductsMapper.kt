package com.android.challenge.home.data

import com.android.challenge.home.model.Cash
import com.android.challenge.home.model.Product
import com.android.challenge.home.model.Products
import com.android.challenge.home.model.Spotlight
import io.reactivex.functions.Function
import javax.inject.Inject

class ProductsMapper @Inject constructor() : Function<ProductsRaw, Products> {

    override fun apply(raw: ProductsRaw): Products {
        val spotlight = raw.spotlight?.map { it.toModel() }
            ?.filter { it.name.isNotBlank() && it.description.isNotBlank() }
            ?: listOf()

        val products = raw.products?.map { it.toModel() }
            ?.filter { it.name.isNotBlank() && it.description.isNotBlank() }
            ?: listOf()

        val cash = raw.cash.toModel()

        return Products(
            products = products,
            spotlight = spotlight,
            cash = cash
        )
    }

    private fun SpotlightRaw.toModel() = Spotlight(
        name = name.orEmpty(),
        bannerURL = bannerURL.orEmpty(),
        description = description.orEmpty()
    )

    private fun ProductRaw.toModel() = Product(
        name = name.orEmpty(),
        imageURL = imageURL.orEmpty(),
        description = description.orEmpty()
    )

    private fun CashRaw?.toModel() = Cash(
        title = this?.title.orEmpty(),
        bannerURL = this?.bannerURL.orEmpty(),
        description = this?.description.orEmpty()
    )
}
