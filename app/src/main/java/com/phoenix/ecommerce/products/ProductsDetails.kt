package com.phoenix.ecommerce.products

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.utils.FilledButton
import com.phoenix.ecommerce.utils.OutlinedButton


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductsScreen(navController: NavController){
val productName : String = "Text Product"
val context = LocalContext.current
Scaffold (

    topBar = {
        TopAppBar(
//            colors = TopAppBarColors(
//                titleContentColor = MaterialTheme.colorScheme.primary,
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                actionIconContentColor = MaterialTheme.colorScheme.primary,
//                scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
//                navigationIconContentColor = MaterialTheme.colorScheme.primary
//            ),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = " Back Button" )
                }
            },
            title = {
            Text(text = productName)
        })
    },

    bottomBar = {

        Column (Modifier.padding(vertical = 40.dp)){
            OutlinedButton("Add to Cart"){
                Toast.makeText(context, "Pressed Add Cart", Toast.LENGTH_SHORT).show()

            }
            FilledButton("Buy Now"){
                Toast.makeText(context, "Pressed Buy Now", Toast.LENGTH_SHORT).show()
            }
        }
    },

    content = {
        innerPadding->
        Surface(
            modifier = Modifier.padding(innerPadding)) {


            LazyColumn {

                // Image of our product
                item {
                    Column {

                        // adding horizontal pager to our app so that we can scroll it like a page
                        val pagerState = rememberPagerState(pageCount = { 3 })
                        HorizontalPager(
                            modifier = Modifier
                                .wrapContentHeight(),
                            state = pagerState
                        ) { page ->


                            ProductImageView()


                        }
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(pagerState.pageCount) {
                                val color =
                                    if (pagerState.currentPage == it) {
                                        Color.DarkGray
                                    } else {
                                        Color.LightGray
                                    }

                                Spacer(modifier = Modifier.height(20.dp))
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(8.dp)
                                )
                            }
                        }
                    }
                }
                // Product Detail
                item {
                ProductDetail(productName = "Fender Stratocaster", productCost = "$1000", productRating = "5")

                }
                item {
                    ProductAddQuantitySelector()
                }


        }}

    }
)




    ProductSpecs()
    ProductsCustomerReview()


}

@Preview(showSystemUi = true)
@Composable
fun ProductScreenView(){
    ProductsScreen(navController = rememberNavController())
}