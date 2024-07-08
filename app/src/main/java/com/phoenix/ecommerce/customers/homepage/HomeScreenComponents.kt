package com.phoenix.ecommerce.customers.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.R
import com.phoenix.ecommerce.customers.products.ProductImageView
import com.phoenix.ecommerce.customers.products.ProductImageViewHomePage
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.SharedViewModel

@Composable
fun OfferBanner(products: ArrayList<Products>, navController: NavController){

    Column {

        // adding horizontal so that we can scroll it like a page
        val pagerState = rememberPagerState(pageCount = { products.size })
        HorizontalPager(
            modifier = Modifier
                .wrapContentHeight(),
            state = pagerState
        ) { page ->

            ProductImageViewHomePage(products[page], navController = navController)

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
                        Color.LightGray
                    } else {
                        Color.DarkGray
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

@Composable
fun EachProducts(products: Products, navController: NavController){


    val sharedViewModel : SharedViewModel = viewModel()
    Box(modifier = Modifier
        .width(150.dp)
        .wrapContentHeight()
        ){
        Column(
            modifier = Modifier.clickable {
                sharedViewModel.addProduct(products)
                navController.navigate(Routes.PRODUCT_SCREEN +"/${products.productId}/${products.productCategory}")
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(150.dp)
            ) {
                
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = products.productIconUrl, contentDescription = "")
                    }

            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontSize = 20.sp,
                    text = products.productName)

                Text(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "AU$ " + products.productCost.toString())
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}