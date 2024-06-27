package com.phoenix.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.phoenix.ecommerce.cart.CartScreen
import com.phoenix.ecommerce.homepage.HomeScreen
import com.phoenix.ecommerce.login.LoginScreen
import com.phoenix.ecommerce.products.ProductsScreen
import com.phoenix.ecommerce.profile.ProfileScreen
import com.phoenix.ecommerce.search.SearchScreen


@Composable
fun NavigationGraph(navHostController: NavHostController){

    NavHost(navController = navHostController, startDestination = Routes.LOGIN_SCREEN) {

        composable(Routes.HOME_SCREEN){
            HomeScreen(navHostController)
        }
        composable(Routes.LOGIN_SCREEN){
            LoginScreen(navHostController)
        }
        composable(Routes.CART_SCREEN) {
            CartScreen(navHostController)
        }
        composable(Routes.PRODUCT_SCREEN) {
            ProductsScreen(navHostController)
        }
        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(navHostController)
        }
        composable(Routes.PROFILE_SCREEN) {
            ProfileScreen(navHostController)
        }

    }
}