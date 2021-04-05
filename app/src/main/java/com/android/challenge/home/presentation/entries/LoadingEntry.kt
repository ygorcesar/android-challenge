package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

class LoadingEntry : Item() {

    override fun getLayout(): Int = R.layout.home_loading_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = Unit
}