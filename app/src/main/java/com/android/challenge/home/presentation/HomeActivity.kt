package com.android.challenge.home.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.challenge.R
import com.android.challenge.base.common.exception.AppException
import com.android.challenge.base.extensions.failure
import com.android.challenge.base.extensions.observe
import com.android.challenge.base.extensions.provideViewModel
import com.android.challenge.home.presentation.entries.ErrorEntry
import com.android.challenge.home.presentation.entries.LoadingEntry
import com.android.challenge.home.viewmodel.HomeViewModel
import com.xwray.groupie.GroupieAdapter
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity(R.layout.home_activity) {

    private val viewModel: HomeViewModel by provideViewModel()
    private val groupieAdapter by lazy { GroupieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
        setupViews()
        loadHome()
    }

    private fun setupObservers() = with(viewModel) {
        observe(products, ::onProductsChanged)
        observe(loading, ::onLoading)
        failure(appException, ::onResponseError)
    }

    private fun setupViews() {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = groupieAdapter
        }
    }

    private fun loadHome() {
        viewModel.fetchProducts()
    }

    private fun onProductsChanged(presentation: ProductsPresentation?) {
        if (presentation == null) return
        groupieAdapter.update(presentation.entries)
    }

    private fun onLoading(isLoading: Boolean?) {
        if (true == isLoading) {
            groupieAdapter.update(listOf(LoadingEntry()))
        }
    }

    private fun onResponseError(appException: AppException?) {
        val entry = listOf(ErrorEntry(appException, ::loadHome))
        groupieAdapter.update(entry)
    }
}
