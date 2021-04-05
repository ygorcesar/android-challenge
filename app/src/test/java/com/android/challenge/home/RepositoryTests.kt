package com.android.challenge.home

import com.android.challenge.base.BaseTests
import com.android.challenge.base.common.exception.NetworkError
import com.android.challenge.home.data.ApiServiceGateway
import com.android.challenge.home.data.HomeRepository
import com.android.challenge.home.data.ProductsMapper
import com.android.challenge.home.model.Products
import org.junit.Before
import org.junit.Test
import retrofit2.create
import testCommon.di.retrofit
import testCommon.utils.*

class RepositoryTests : BaseTests() {

    private val service: ApiServiceGateway = retrofit.create()

    private val productsMapper = ProductsMapper()

    private lateinit var repository: HomeRepository.Remote

    private val products: Products by lazy { productsMapper.apply(JsonFile.PRODUCTS.fromJson()) }

    override val isMockServerEnabled: Boolean get() = true

    @Before
    override fun setUp() {
        super.setUp()
        repository = HomeRepository.Remote(service, productsMapper, networkHandler)
    }

    @Test
    fun `should return list of products when request to get products information`() {
        stubNetworkHandlerIsConnected(true)

        val testObserver = repository.getProducts().test()

        with(testObserver) {
            awaitTerminalEvent()
            assertCompleted(products)
            assertNoErrors()
        }
    }

    @Test
    fun `should throws network error when try to get products without internet connection`() {
        stubNetworkHandlerIsConnected(false)

        val testObserver = repository.getProducts().test()

        testObserver.assertWithError(NetworkError)
    }
}
