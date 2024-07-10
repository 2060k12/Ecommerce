package com.phoenix.ecommerce.customers.homepage

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.phoenix.ecommerce.utils.BottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    val viewModel = HomeScreenViewModel()
    viewModel.getAllComputers()
    viewModel.getAllMobiles()
    viewModel.getAllWatches()

    val computerList = viewModel.computerList.observeAsState(initial = ArrayList()).value
    val mobileList = viewModel.mobileList.observeAsState(initial = ArrayList()).value
    val watchList = viewModel.watchList.observeAsState(initial = ArrayList()).value
    val offerList = viewModel.offerList.observeAsState(initial = ArrayList()).value

    viewModel.getAllOffers()

    Surface(modifier = Modifier.fillMaxSize()) {
        
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = {
                    Text(text = "Home")
                })
            },

            bottomBar = {
               BottomNavBar(navController = navController, "home")
            }
        
        ) {
            innerPadding->

            Surface(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                ) {
                Column(

                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                )
                {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "G'day,")
                    Text(
                        modifier = Modifier.padding(8.dp,8.dp,8.dp, 16.dp ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "Today's Deal,")

                     OfferBanner(offerList, navController)

                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "Computers")


                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {


                            items(computerList){
                                EachProducts(products = it , navController)
                            }

                    }
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "Mobiles")

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {


                        items(mobileList){
                            EachProducts(products = it , navController)
                        }

                    }

                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        text = "Watches")

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)) {


                        items(watchList){
                            EachProducts(products = it , navController)
                        }

                    }

                }

            }



        }

    }

}