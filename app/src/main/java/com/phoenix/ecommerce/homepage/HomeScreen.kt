package com.phoenix.ecommerce.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.play.integrity.internal.i
import com.google.android.play.integrity.internal.s
import com.phoenix.ecommerce.utils.BottomNavBar
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val viewModel = HomeScreenViewModel()
    viewModel.getAllProducts()
    val productList = viewModel.productList.observeAsState(initial = ArrayList()).value

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


                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "Guitars")


                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {


                            items(productList){
                                EachProducts(products = it , navController)
                            }

                    }

                }

            }



        }

    }

}