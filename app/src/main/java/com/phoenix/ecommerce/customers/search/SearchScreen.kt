package com.phoenix.ecommerce.customers.search

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.admin.AdminViewModel
import com.phoenix.ecommerce.customers.homepage.EachProducts
import com.phoenix.ecommerce.customers.homepage.HomeScreenViewModel
import com.phoenix.ecommerce.customers.products.ProductsViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.navigation.RoutesAdmin
import com.phoenix.ecommerce.utils.BottomNavBar
import com.phoenix.ecommerce.utils.IndeterminateCircularIndicator
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, sharedViewModel: SharedViewModel){

    val viewModel :HomeScreenViewModel = viewModel()
    val allProducts = viewModel.productList.observeAsState().value
    val context = LocalContext.current
    val searchInputText = remember{ mutableStateOf("")}
    val loading = viewModel.isLoading.observeAsState(true)
    viewModel.getAllProducts()
    var searchProducts = allProducts


    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {

                        Icon(painter = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowBack) , contentDescription = "Back")
                    }
                },
                title = {
                Text(text = "Search")
            }
            
            )
            
        },
        bottomBar = {
            BottomNavBar(navController = navController, "search")
        },

        content = {
            innerPadding->
            
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                IndeterminateCircularIndicator(loading = loading.value)
                
            Column (
                modifier = Modifier
                    .fillMaxSize()
            ){
                
                SearchScreenBar(
                    value = searchInputText.value,
                    onValueChange = {
                        val temp = ArrayList<Products>()

                        searchInputText.value = it
                        if (allProducts != null) {
                            for (product in allProducts){
                                if(product.productName.lowercase().contains(searchInputText.value.lowercase())){
                                    temp.add(product)
                                }
                            }
                            searchProducts = temp

                        }
                        if(searchInputText.value.isEmpty()){
                            searchProducts = allProducts
                        }


                    })
                LazyVerticalGrid(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    columns = GridCells.Fixed(2)) {
                    if(searchProducts != null){
                       items(searchProducts!!){
                                product ->
                                   Card(
                                       onClick = {
                                           if (product.productId.isNotEmpty()) {
                                               sharedViewModel.addProduct(product)
                                               navController.navigate(Routes.PRODUCT_SCREEN + "/${product.productId}/${product.productCategory}")
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
        }}

    )

}

