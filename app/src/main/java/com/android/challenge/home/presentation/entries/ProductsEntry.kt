package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.home.model.Product
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.home_products_item_view.view.*

data class ProductsEntry(private val products: List<Product>) : Item() {

    private val groupieAdapter by lazy { GroupieAdapter() }

    override fun getLayout(): Int = R.layout.home_products_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = with(viewHolder.itemView) {
        val entries = products.map { ProductEntry(it) }
        productsPager.adapter = groupieAdapter
        groupieAdapter.update(entries)
    }
}