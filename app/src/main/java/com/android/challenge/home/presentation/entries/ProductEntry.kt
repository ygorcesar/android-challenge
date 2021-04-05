package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.base.extensions.view.loadImage
import com.android.challenge.home.model.Product
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.home_product_item_view.view.*

data class ProductEntry(private val product: Product) : Item() {

    override fun getLayout(): Int = R.layout.home_product_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = with(viewHolder.itemView) {
        productImage.loadImage(product.imageURL)
    }
}