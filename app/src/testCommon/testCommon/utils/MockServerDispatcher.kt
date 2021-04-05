package testCommon.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            isGetProducts(request) -> responseGetProducts()
            else -> MockResponse().setResponseCode(404)
        }
    }

    /**
     * Service
     * */
    private fun responseGetProducts() = successResponse().setBody(JsonFile.PRODUCTS.json)

    /**
     * Helpers to check and get a correct response
     * */

    private fun isGetProducts(request: RecordedRequest) =
        (request.path ?: "").startsWith(URL_PRODUCTS) && METHOD_GET == request.method

    private fun successResponse() = MockResponse().setResponseCode(200)

}

private const val METHOD_GET = "GET"
private const val URL_PRODUCTS = "/products"
