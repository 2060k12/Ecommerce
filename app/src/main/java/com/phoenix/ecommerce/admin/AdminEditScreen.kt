package com.phoenix.ecommerce.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.customers.homepage.EachProducts
import com.phoenix.ecommerce.customers.homepage.HomeScreenViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.RoutesAdmin
import com.phoenix.ecommerce.utils.AdminNavigationBar
import com.phoenix.ecommerce.utils.SharedViewModel
import okhttp3.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditScreen(navController: NavController, sharedViewModel: SharedViewModel){
    
    // using products viewModel
    val viewModel : HomeScreenViewModel = viewModel()
    val productList = viewModel.productList.observeAsState().value
    viewModel.getAllProducts()
    var tempList = remember { mutableListOf(Products()) }
    val searchField = remember {
        mutableStateOf("")
    }

Scaffold(
    topBar = {
        TopAppBar(title = {
            Text(text = "Current Listing")

        })
    },

    bottomBar = {
        BottomAppBar {
            AdminNavigationBar(navController = navController)
        }
    }
) {innerPadding->
    Surface( modifier =  Modifier.padding(innerPadding)) {

        Column {

            TextField(
                placeholder = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Search , contentDescription ="search" )
                        Text(text = "Search")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = searchField.value, onValueChange = {
                    searchField.value = it
                    if (productList != null) {
                        for(product in productList){
                            if(product.productName.contains(searchField.value)){
                                tempList.add(product)
                            }
                        }
                    }

                })

            LazyVerticalGrid(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Fixed(2)
            ) {
                if (productList?.isNotEmpty() == true) {

                    items(productList) { product ->
                        Card(
                            onClick = {
                                if (product.productId.isNotEmpty()) {
                                    sharedViewModel.addProduct(product)
                                    navController.navigate(RoutesAdmin.AdminEditEachProductScreen)
                                }
                            }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier.size(200.dp),
                                    contentScale = ContentScale.Crop,
                                    model = product.productIconUrl, contentDescription = ""
                                )
                                Box(
                                    modifier = Modifier
                                        .height(30.dp)
                                ) {
                                    Text(
                                        text = product.productName
                                    )
                                }
                            }
                        }

                    }

                }
            }
        }
        
    }

}

}