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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.navigation.NavController
import com.phoenix.ecommerce.navigation.Routes

@Composable
fun BottomNavBar(navController: NavController){
    BottomAppBar {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            // Home Screen
            IconButton(
                onClick = { navController.navigate(Routes.HOME_SCREEN){
                    popUpTo(navController.graph.startDestinationId)
                } },
            ) {
                Icon(painter = rememberVectorPainter(image = Icons.Default.Home), contentDescription = "Home")
            }

            // Search Screen

            IconButton(onClick = {
//                navController.navigate(Routes.SEARCH_SCREEN)
            }) {
                Icon(painter = rememberVectorPainter(image = Icons.Default.Search), contentDescription = "Search")
            }

            // Cart Screen
            IconButton(onClick = {
                navController.navigate(Routes.CART_SCREEN)

            }) {
                Icon(painter = rememberVectorPainter(image = Icons.Default.ShoppingCart), contentDescription = "Cart")
            }

            // Profile Screen
            IconButton(onClick = {
//                navController.navigate(Routes.CART_SCREEN)
            }) {
                Icon(painter = rememberVectorPainter(image = Icons.Default.AccountCircle), contentDescription = "Profile")
            }
        }
    }
}