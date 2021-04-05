package com.android.challenge.home

import com.android.challenge.base.BaseViewModelTests
import com.android.challenge.base.common.exception.NetworkError
import com.android.challenge.home.data.HomeRepository
import com.android.challenge.home.model.Products
import com.android.challenge.home.presentation.ProductsPresentation
import com.android.challenge.home.viewmodel.HomeViewModel
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.subjects.SingleSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import testCommon.home.PRODUCTS

class HomeViewModelTests : BaseViewModelTests() {

    @MockK(relaxed = true)
    lateinit var mockRepository: HomeRepository


    private lateinit var viewModel: HomeViewModel

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = HomeViewModel(mockRepository)
    }

    @Test
    fun `should return products when get products called to fetch on repository`() {
        // given
        val products = PRODUCTS
        val expected = ProductsPresentation(products)
        val publish = SingleSubject.create<Products>()
        stubProductsRepository(publish)

        // when
        viewModel.fetchProducts()

        // then
        assertThat(viewModel.loading.value).isEqualTo(true)

        publish.onSuccess(products)

        assertThat(viewModel.loading.value).isEqualTo(false)
        assertThat(viewModel.products.value).isEqualTo(expected)
    }

    @Test
    fun `should throw network error when try to get products on repository without internet`() {
        // given
        val publish = SingleSubject.create<Products>()
        stubProductsRepository(publish)

        // when
        viewModel.fetchProducts()


        // then
        publish.onError(NetworkError)
        assertThat(viewModel.loading.value).isEqualTo(false)
        assertThat(viewModel.appException.value).isEqualTo(NetworkError)
    }

    private fun stubProductsRepository(singleSubject: SingleSubject<Products>) {
        every { mockRepository.getProducts() } returns singleSubject
    }
}
