package com.phoenix.ecommerce.homepage

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.google.android.play.integrity.internal.s
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.utils.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val viewModel = HomeScreenViewModel()


    Surface(modifier = Modifier.fillMaxSize()) {
        
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { 
                    Text(text = "Home")
                })
            },

            bottomBar = {
               BottomNavBar(navController = navController)
            }
        
        ) {
            innerPadding->

            Surface(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                ) {
                Column(
                    modifier = Modifier.fillMaxSize()

                )
                {
                     OfferBanner()


                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                        ){

                       items(10){
                           EachProducts(Products("Try Product","1223131","$20","https://images.unsplash.com/photo-1719150016704-270c5a0deee4?w=800&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHx8"))


                    }}
                }

            }



        }

    }

}