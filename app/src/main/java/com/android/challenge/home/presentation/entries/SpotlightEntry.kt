package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.base.extensions.view.loadImage
import com.android.challenge.home.model.Spotlight
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.home_spotlight_item_view.view.*

data class SpotlightEntry(private val spotlight: Spotlight) : Item() {

    override fun getLayout(): Int = R.layout.home_spotlight_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = with(viewHolder.itemView) {
        spotlightBanner.loadImage(spotlight.bannerURL)
    }
}