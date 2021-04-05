package com.android.challenge.home.presentation

import com.android.challenge.home.model.Products
import com.android.challenge.home.presentation.entries.CashEntry
import com.android.challenge.home.presentation.entries.ProductsEntry
import com.android.challenge.home.presentation.entries.SpotlightsEntry
import com.xwray.groupie.kotlinandroidextensions.Item

data class ProductsPresentation(val entries: List<Item>) {

    companion object {
        operator fun invoke(products: Products): ProductsPresentation {
            val entries = mutableListOf<Item>().apply {
                if (products.spotlight.isNotEmpty()) add(SpotlightsEntry(products.spotlight))
                if (products.cash.isValid) add(CashEntry(products.cash))
                if (products.products.isNotEmpty()) add(ProductsEntry(products.products))
            }

            return ProductsPresentation(entries)
        }
    }
}