package com.android.challenge.home.data

import com.android.challenge.base.common.network.NetworkHandler
import com.android.challenge.base.data.BaseRemoteRepository
import com.android.challenge.home.model.Products
import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {

    fun getProducts(): Single<Products>

    class Remote @Inject constructor(
        private val service: ApiServiceGateway,
        private val productsMapper: ProductsMapper,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), HomeRepository {

        override fun getProducts(): Single<Products> = request(productsMapper) {
            service.getProducts()
        }
    }
}