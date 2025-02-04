package com.phoenix.ecommerce.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.phoenix.ecommerce.admin.ui.AddImagesScreen
import com.phoenix.ecommerce.admin.ui.AddProductScreen
import com.phoenix.ecommerce.admin.ui.AddProductSecondScreen
import com.phoenix.ecommerce.admin.ui.AdminEditEachProductScreen
import com.phoenix.ecommerce.admin.ui.AdminEditScreen
import com.phoenix.ecommerce.admin.ui.AdminScreen
import com.phoenix.ecommerce.customers.cart.CartScreen
import com.phoenix.ecommerce.customers.checkout.CheckOutScreen
import com.phoenix.ecommerce.customers.homepage.HomeScreen
import com.phoenix.ecommerce.customers.products.ProductReviewScreen
import com.phoenix.ecommerce.customers.products.ProductsScreen
import com.phoenix.ecommerce.customers.profile.EditProfileScreen
import com.phoenix.ecommerce.customers.profile.OrderHistoryScreen
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
        // products review screen
        composable(Routes.PRODUCT_REVIEW_SCREEN) {
            ProductReviewScreen(navController = navHostController, sharedViewModel)
        }
        // add images screen
        composable(Routes.ADD_IMAGES_SCREEN) {
            AddImagesScreen(sharedViewModel, navHostController)
        }


        // Home Screen
        composable(Routes.HOME_SCREEN) {
            HomeScreen(navHostController)
        }

        // Login Screen
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navHostController)
        }

        // edit Profile Screen
        composable(Routes.EDIT_PROFILE_SCREEN) {
            EditProfileScreen(navController = navHostController, sharedViewModel)

        }

        // Product Screen -> Single product detail view
        composable(Routes.PRODUCT_SCREEN + "/{productId}/{category}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val category = backStackEntry.arguments?.getString("category")
            if (productId != null) {
                if (category != null) {
                    ProductsScreen(
                        sharedViewModel = sharedViewModel,
                        navController = navHostController,
                        productId = productId,
                        category
                    )
                }
            }

        }

        // order history screen
        composable(Routes.ORDERS_HISTORY_SCREEN) {

            OrderHistoryScreen(navController = navHostController)
        }

        // Search Screen
        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(navHostController, sharedViewModel)
        }

        // Profile Screen
        composable(Routes.PROFILE_SCREEN) {
            ProfileScreen(navHostController, sharedViewModel)
        }

        // Cart Screen
        composable<RoutesAdmin.CartScreen> {
            CartScreen(navHostController, sharedViewModel)
        }

        // CHeckOut Screen
        composable<RoutesAdmin.CheckOutScreen> {
            CheckOutScreen(sharedViewModel, navHostController)
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
        } // Admin Dashboard

        composable<RoutesAdmin.AdminEditScreen> {
            AdminEditScreen(navHostController, sharedViewModel)
        }

        composable<RoutesAdmin.AdminEditEachProductScreen> {
            val passedArgument = it.toRoute<RoutesAdmin.AdminEditEachProductScreen>()
            AdminEditEachProductScreen(
                sharedViewModel = sharedViewModel,
                products = sharedViewModel.product,
                navHostController
            )
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

