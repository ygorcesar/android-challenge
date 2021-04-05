package testCommon.home

import com.android.challenge.home.model.*

val SPOTLIGHT_1 = Spotlight("Name 1", "banner_url", "description_1")
val SPOTLIGHT_2 = Spotlight("Name 2", "banner_url", "description_2")
val SPOTLIGHT_3 = Spotlight("Name 3", "banner_url", "description_3")

val SPOTLIGHTS = listOf(SPOTLIGHT_1, SPOTLIGHT_2, SPOTLIGHT_3)

val PRODUCT_1 = Product("Product 1", "image_url", "description_1")
val PRODUCT_2 = Product("Product 2", "image_url", "description_2")
val PRODUCT_3 = Product("Product 3", "image_url", "description_3")

val PRODUCT_ITEMS = listOf(PRODUCT_1, PRODUCT_2, PRODUCT_3)

val CASH = Cash("Title 1", "banner_url", "description")

val PRODUCTS = Products(SPOTLIGHTS, PRODUCT_ITEMS, CASH)





