package com.phoenix.ecommerce.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.navigation.RoutesAdmin

@Composable
fun BottomNavBar(navController: NavController, selected : String){
    NavigationBar {
        // Home Screen
            NavigationBarItem(
                selected = selected == "home",
                onClick = {

                    navController.navigate(Routes.HOME_SCREEN){
                        popUpTo(navController.graph.startDestinationId)
                    } },
                icon = { Icon(painter = rememberVectorPainter(image = Icons.Default.Home), contentDescription = "Home")})


         // Search Screen
            NavigationBarItem(
                selected = selected == "search" ,
                onClick = {

                    navController.navigate(Routes.SEARCH_SCREEN)},
                icon = { Icon(painter = rememberVectorPainter(image = Icons.Default.Search), contentDescription = "search")})


         // cart Screen
            NavigationBarItem(
                selected = selected == "cart" ,
                onClick = {
                    navController.navigate(RoutesAdmin.CartScreen) },
                icon = { Icon(painter = rememberVectorPainter(image = Icons.Default.ShoppingCart), contentDescription = "cart")})


         // profile Screen
            NavigationBarItem(
                selected = selected == "profile" ,
                onClick = {
                    navController.navigate(Routes.PROFILE_SCREEN){
                        popUpTo(navController.graph.startDestinationId)
                    } },
                icon = { Icon(painter = rememberVectorPainter(image = Icons.Default.AccountCircle), contentDescription = "profile")})

    }
}