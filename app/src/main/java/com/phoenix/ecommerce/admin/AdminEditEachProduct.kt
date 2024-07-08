package com.phoenix.ecommerce.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.phoenix.ecommerce.customers.products.ProductDetail
import com.phoenix.ecommerce.customers.products.ProductImageView
import com.phoenix.ecommerce.customers.products.ProductsInfo
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.utils.AdminNavigationBar
import kotlinx.serialization.json.JsonNull.content


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditEachProductScreen(products: Products, navController: NavController){

    val viewModel : AdminViewModel = viewModel()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Edit Product")
            })
        },
        bottomBar = {
            BottomAppBar {
                Column {
                    Button(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        onClick = {
                            viewModel.addProductToSpotlight(products)

                        }) {
                        Text(text = "Add product to Spotlight")

                    }
                }
            }
        },
        content = {
                innerPadding->

            Surface(modifier = Modifier.padding(innerPadding)) {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {

                ProductImageView(products = products, navController)
                ProductDetail(products = products)
                ProductsInfo(products = products)

            }
            }

        }
    )


}