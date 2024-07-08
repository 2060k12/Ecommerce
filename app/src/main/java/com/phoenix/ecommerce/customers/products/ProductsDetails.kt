package com.phoenix.ecommerce.customers.products

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.customers.cart.CartViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.navigation.RoutesAdmin
import com.phoenix.ecommerce.utils.FilledButton
import com.phoenix.ecommerce.utils.OutlinedButton
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductsScreen(sharedViewModel: SharedViewModel, navController: NavController, productId: String, category: String){
val context = LocalContext.current

    // initializing viewmodel
    val viewModel : ProductsViewModel = viewModel()

    // cart view model
    val cartViewModel : CartViewModel = viewModel()

    viewModel.getProduct(productId, category)
    val product : Products? = viewModel.clickedProduct.value

    val selectedColor = remember {
        mutableStateOf("")
    }

    val selectedSpecs = remember {
        mutableStateOf("")
    }

Scaffold (

    topBar = {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = " Back Button" )
                }
            },
            title = {
                product?.productName?.let { Text(text = it) }
        })
    },

    bottomBar = {

        Column (
            Modifier
                .wrapContentHeight()
                .padding(bottom = 30.dp)){
            OutlinedButton("Add to Cart"){

                cartViewModel.getItemByID(productId){
                    cartProduct ->

                    // TODO: fix the cart page
                    // TODO: same products with different specs or color are alos getting merged into a same one
                    if(cartProduct == null){
                        val tempCart = product?.let {
                            CartProduct(
                                productId = productId,
                                productName = product.productName,
                                productCost = product.productCost,
                                productInfo = product.productInfo,
                                productIconUrl = product.productIconUrl,
                                productCount = 1,
                                productColor = selectedColor.value ,
                                productSpec = selectedSpecs.value,
                                productCategory = product.productCategory,

                                )
                        }
                        if (tempCart != null) {
                            cartViewModel.addToCart(tempCart)
                        }
                    }

                    else{
                        val tempCart = cartProduct.copy()
                        tempCart.productCount = tempCart.productCount?.plus(1)
                        cartViewModel.updateCart(tempCart)
                    }
                }


                Toast.makeText(context, "Pressed Add Cart", Toast.LENGTH_SHORT).show()

            }
            FilledButton("View Cart"){
                navController.navigate(RoutesAdmin.CartScreen)
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

                            if (product != null) {
                                ProductImageView(product, navController)
                            }
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
                    if (product != null) {
                        ProductDetail(product)
                    }

                }



                // if the productColor is empty this item wont be shown in the lazy column
                if(product?.productColor?.isNotEmpty() == true)
                item {
                    ProductsChoseOptions(product.productColor){
                        selectedColor.value = it
                    }

                }
                // if the productColor is empty this item wont be shown in the lazy column
                if(product?.productSpecs?.isNotEmpty() == true)
                    item {
                        ProductsChoseOptions(product.productSpecs){
                            selectedSpecs.value = it
                        }

                    }

                item {
                    if (product != null) {
                        ProductsInfo(product)
                    }
                }

                item {
                    if (product != null) {
                        sharedViewModel.addProduct(product)
                        ProductsCustomerReview(navController)
                    }
                }



            }}

    }
)




}

@Preview(showSystemUi = true)
@Composable
fun ProductScreenView(){
    ProductsScreen(sharedViewModel = SharedViewModel() ,navController = rememberNavController(), productId = "", "")
}