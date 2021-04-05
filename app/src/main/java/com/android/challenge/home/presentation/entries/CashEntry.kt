package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.base.extensions.view.loadImage
import com.android.challenge.home.model.Cash
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.home_cash_item_view.view.*

data class CashEntry(private val cash: Cash) : Item() {

    override fun getLayout(): Int = R.layout.home_cash_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = with(viewHolder.itemView) {
        cashTitle.text = cash.title
        cashImage.loadImage(cash.bannerURL)
    }
}