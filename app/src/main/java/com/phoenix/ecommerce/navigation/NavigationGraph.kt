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
import com.phoenix.ecommerce.checkout.CheckOutScreen
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.homepage.HomeScreen
import com.phoenix.ecommerce.login.LoginScreen
import com.phoenix.ecommerce.login.SignUpScreen
import com.phoenix.ecommerce.products.ProductsScreen
import com.phoenix.ecommerce.profile.ProfileScreen
import com.phoenix.ecommerce.search.SearchScreen
import com.phoenix.ecommerce.utils.SharedViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(navHostController: NavHostController, startDestination : String){

    val sharedViewModel : SharedViewModel = viewModel()
    NavHost(navController = navHostController, startDestination = startDestination) {

        // Home Screen
        composable(Routes.HOME_SCREEN){
            HomeScreen(navHostController)
        }

        // Login Screen
        composable(Routes.LOGIN_SCREEN){
            LoginScreen(navHostController)
        }

        // Cart Screen
        composable(Routes.CART_SCREEN ) {
            CartScreen(navHostController)
        }

        // Product Screen -> Single product detail view
        composable(Routes.PRODUCT_SCREEN + "/{productId}") {
                backStackEntry->
                val productId =backStackEntry.arguments?.getString("productId")
            if (productId != null) {
                ProductsScreen(navController =  navHostController, productId = productId )
            }

        }

        // Search Screen
        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(navHostController)
        }

        // Profile Screen
        composable(Routes.PROFILE_SCREEN) {
            ProfileScreen(navHostController)
        }

        // CHeckOut Screen
        composable(Routes.CHECKOUT_SCREEN){
            CheckOutScreen()
        }

        // SIGNUP SCREEN
        composable(Routes.SIGNUP_SCREEN){
            SignUpScreen(navHostController)
        }

    }
}