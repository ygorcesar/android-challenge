package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.home.model.Spotlight
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.home_spotlights_item_view.view.*

data class SpotlightsEntry(private val spotlights: List<Spotlight>) : Item() {

    private val groupieAdapter by lazy { GroupieAdapter() }

    override fun getLayout(): Int = R.layout.home_spotlights_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) = with(viewHolder.itemView) {
        val entries = spotlights.map { SpotlightEntry(it) }
        spotlightPager.adapter = groupieAdapter
        groupieAdapter.update(entries)
    }
}