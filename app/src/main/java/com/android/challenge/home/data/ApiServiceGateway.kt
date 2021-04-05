package com.android.challenge.home.data

import io.reactivex.Single
import retrofit2.http.GET

interface ApiServiceGateway {

    @GET("products")
    fun getProducts(): Single<ProductsRaw>
}