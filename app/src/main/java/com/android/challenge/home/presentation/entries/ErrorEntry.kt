package com.android.challenge.home.presentation.entries

import com.android.challenge.R
import com.android.challenge.base.presentation.views.ErrorView
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

data class ErrorEntry(
    private val error: Throwable?,
    private val onTryAgain: () -> Unit
) : Item() {

    override fun getLayout(): Int = R.layout.error_item_view

    override fun bind(viewHolder: GroupieViewHolder, position: Int) =
        with(viewHolder.itemView as ErrorView) {
            showError(error)
            setActionButtonClick { onTryAgain() }
        }
}