package com.phoenix.ecommerce.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController){
    val viewModel : CartViewModel = viewModel()
    val cartList = viewModel.cartList.observeAsState(initial = ArrayList()).value

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Cart")
            })
        },

        bottomBar = {
            Column {
                Button(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    onClick = {

                        // Navigates to checkout screen
                        navController.navigate(Routes.CHECKOUT_SCREEN)

                    }) {
                    Text(text = "Checkout")

                }
                BottomNavBar(navController)
            }

        }

    ) {
        innerPadding ->

        Surface(
            modifier = Modifier.padding(innerPadding)) {

            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()) {

                items(cartList){
                    EachCartItem(cartProduct = it )

                }
            }



        }
    }
}