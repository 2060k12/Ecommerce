package com.phoenix.ecommerce.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.navigation.RoutesAdmin

@Composable
fun AdminNavigationBar(navController: NavController, selected: String) {


    NavigationBar {


        NavigationBarItem(selected = selected == "dashboard",
            onClick = {
                // navigates to Admin Dashboard
                navController.navigate(Routes.ADMIN_DASHBOARD)

            }, icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Dashboard")
            })

        NavigationBarItem(selected = selected =="add"
            , onClick = {
                // navigates to Admin Add new product
                navController.navigate(RoutesAdmin.AdminAddScreenOne)


            }, icon = {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Add")
            })


        NavigationBarItem(selected = selected == "products"
            , onClick = {
                // navigates to Current products page
                navController.navigate(RoutesAdmin.AdminEditScreen)

            }, icon = {
                Icon(imageVector = Icons.Default.Info, contentDescription = "products")
            })
    }
}