package com.phoenix.ecommerce.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.phoenix.ecommerce.utils.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Cart")
            })
        },

        bottomBar = {
            BottomNavBar(navController)
        }

    ) {
        innerPadding ->

        Surface(modifier = Modifier.padding(innerPadding)) {

            Text(text = "Hello")
        }
    }
}