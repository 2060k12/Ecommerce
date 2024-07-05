package com.phoenix.ecommerce.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.phoenix.ecommerce.admin.AddProductScreen
import com.phoenix.ecommerce.admin.AddProductSecondScreen
import com.phoenix.ecommerce.admin.AdminScreen
import com.phoenix.ecommerce.customers.cart.CartScreen
import com.phoenix.ecommerce.customers.checkout.CheckOutScreen
import com.phoenix.ecommerce.customers.homepage.HomeScreen
import com.phoenix.ecommerce.customers.products.ProductsScreen
import com.phoenix.ecommerce.customers.profile.ProfileScreen
import com.phoenix.ecommerce.customers.search.SearchScreen
import com.phoenix.ecommerce.login.LoginScreen
import com.phoenix.ecommerce.login.SignUpScreen
import com.phoenix.ecommerce.utils.SharedViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(navHostController: NavHostController, startDestination: String) {

    val sharedViewModel: SharedViewModel = viewModel()
    // TODO: change to start destination

    NavHost(navController = navHostController, startDestination = startDestination) {

//    NavHost(navController = navHostController, startDestination = RoutesAdmin.AdminAddScreenOne ) {

        // Home Screen
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }

        // Login Screen
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navHostController)
        }


        // Product Screen -> Single product detail view
        composable(Routes.PRODUCT_SCREEN + "/{productId}/{category}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val category = backStackEntry.arguments?.getString("category")
            if (productId != null) {
                if (category != null) {
                    ProductsScreen(
                        navController = navHostController,
                        productId = productId,
                        category
                    )
                }
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

        // Cart Screen
        composable<RoutesAdmin.CartScreen> {
            CartScreen(navHostController, sharedViewModel)
        }

        // CHeckOut Screen
        composable<RoutesAdmin.CheckOutScreen> {
            CheckOutScreen(sharedViewModel)
        }

        // SIGNUP SCREEN
        composable(Routes.SIGNUP_SCREEN) {
            SignUpScreen(navHostController)
        }


        // Navigation for admin Panel
        // Only User who is logged in as an admin will be able to go to these screens

        // Admin Screen
        // Admin Dashboard
        composable(Routes.ADMIN_DASHBOARD) {
            AdminScreen(navHostController)
        }


        // Admin add new product Screen
        composable<RoutesAdmin.AdminAddScreenOne> {
            AddProductScreen(navController = navHostController)
        }

        // Admin add product next page / 2nd page
        composable<RoutesAdmin.AdminAddScreenTwo> {

            val customValue = it.toRoute<RoutesAdmin.AdminAddScreenTwo>()
            AddProductSecondScreen(
                navController = navHostController,
                productName = customValue.productName,
                productCost = customValue.productCost,
                productCategory = customValue.productCategory,
                imageLink = customValue.imageLink,
                productInfo = customValue.productInfo
            )
        }


    }
}

