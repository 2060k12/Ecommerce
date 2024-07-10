package com.phoenix.ecommerce.navigation

import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.utils.SharedViewModel
import kotlinx.serialization.Serializable

object Routes {
    const val PRODUCT_REVIEW_SCREEN = "PRODUCT_REVIEW_SCREEN"
    const val ORDERS_HISTORY_SCREEN = "ORDERS_HISTORY_SCREEN"
    const val EDIT_PROFILE_SCREEN = "EDIT_PROFILE_SCREEN"
    const val ADMIN_DASHBOARD= "ADMIN_DASHBOARD"
    const val ADMIN_ADD= "ADMIN_ADD"
    const val ADMIN_ADD_TWO= "ADMIN_ADD_TWO/{productName}/{productCost}/{productCategory}/{productInfo}/{imageLink}"
    const val LOGIN_SCREEN = "LOGIN_SCREEN"
    const val HOME_SCREEN = "HOME_SCREEN"
    const val CART_SCREEN = "CART_SCREEN"
    const val PRODUCT_SCREEN = "PRODUCT_SCREEN"
    const val SEARCH_SCREEN = "SEARCH_SCREEN"
    const val PROFILE_SCREEN = "PROFILE_SCREEN"
    const val CHECKOUT_SCREEN = "CHECKOUT_SCREEN"
    const val SIGNUP_SCREEN = "SIGNUP_SCREEN"
    const val ADD_IMAGES_SCREEN = "ADD_IMAGES_SCREEN"

}

@Serializable
sealed class RoutesAdmin{

    // admin page dashboard
    @Serializable
    data object AdminDashBoard : RoutesAdmin()


    // admin edit screen
    @Serializable
    data object  AdminEditScreen : RoutesAdmin()

    // admin page Add products screen #1
    @Serializable
    data object AdminAddScreenOne : RoutesAdmin(
    )

    // admin page Add products screen #1
    @Serializable
    data object AdminEditEachProductScreen : RoutesAdmin()

    // admin page Add products screen #2
    @Serializable
    data class AdminAddScreenTwo(
        val productName :String,
        val productCost : String,
        val productInfo : String,
        val imageLink : String,
        val productCategory: String

    ) : RoutesAdmin()


    @Serializable
    data object CartScreen : RoutesAdmin()

    @Serializable
    data object CheckOutScreen : RoutesAdmin()

}