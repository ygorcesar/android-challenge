package com.android.challenge.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.challenge.base.extensions.addToComposite
import com.android.challenge.base.extensions.observeOnBackground
import com.android.challenge.base.presentation.BaseViewModel
import com.android.challenge.home.data.HomeRepository
import com.android.challenge.home.presentation.ProductsPresentation
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _products = MutableLiveData<ProductsPresentation>()

    val loading: LiveData<Boolean> = _loading
    val products: LiveData<ProductsPresentation> = _products

    fun fetchProducts() {
        compositeDisposable.clear()

        repository.getProducts()
            .observeOnBackground()
            .doOnSubscribe { _loading.postValue(true) }
            .doAfterTerminate { _loading.postValue(false) }
            .map(ProductsPresentation::invoke)
            .subscribe(_products::postValue, ::handleFailure)
            .addToComposite(compositeDisposable)
    }
}
