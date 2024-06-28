package com.phoenix.ecommerce.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.phoenix.ecommerce.cart.CartScreen
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.homepage.HomeScreen
import com.phoenix.ecommerce.login.LoginScreen
import com.phoenix.ecommerce.products.ProductsScreen
import com.phoenix.ecommerce.profile.ProfileScreen
import com.phoenix.ecommerce.search.SearchScreen
import com.phoenix.ecommerce.utils.SharedViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(navHostController: NavHostController){

    val sharedViewModel : SharedViewModel = viewModel()

            NavHost(navController = navHostController, startDestination = Routes.LOGIN_SCREEN) {

        composable(Routes.HOME_SCREEN){
            HomeScreen(navHostController)
        }
        composable(Routes.LOGIN_SCREEN){
            LoginScreen(navHostController)
        }
        composable(Routes.CART_SCREEN ) {
            CartScreen(navHostController)
        }

        composable(Routes.PRODUCT_SCREEN + "/{productId}") {
                backStackEntry->
                val productId =backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductsScreen(navController =  navHostController, productId = productId )
            }

        }
        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(navHostController)
        }
        composable(
            Routes.PROFILE_SCREEN) {
            ProfileScreen(navHostController)
        }

    }
}