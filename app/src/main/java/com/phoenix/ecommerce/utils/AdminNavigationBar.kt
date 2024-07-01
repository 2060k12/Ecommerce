package com.phoenix.ecommerce.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
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
fun AdminNavigationBar(navController: NavController) {


    NavigationBar {
        // state of current screen
        val currentScreenDashboard = remember {
            mutableStateOf(true)
        }

        NavigationBarItem(selected = currentScreenDashboard.value,
            onClick = {
                // navigates to Admin Dashboard
                currentScreenDashboard.value = true
                navController.navigate(Routes.ADMIN_DASHBOARD)

            }, icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Dashboard")
            })

        NavigationBarItem(selected = !currentScreenDashboard.value
            , onClick = {
                currentScreenDashboard.value = false
                // navigates to Admin Add new product
                navController.navigate(RoutesAdmin.AdminAddScreenOne)


            }, icon = {
                Icon(imageVector = Icons.Default.Create, contentDescription = "Add")
            })
    }
}