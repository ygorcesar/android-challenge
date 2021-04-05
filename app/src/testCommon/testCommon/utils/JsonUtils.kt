package testCommon.utils

data class JsonMockFile(val path: String, val fileName: String)

object JsonFile {

    private const val PATH = "products/"

    val PRODUCTS = JsonMockFile(PATH, "products.json")

}
