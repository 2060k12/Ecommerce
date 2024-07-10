package com.phoenix.ecommerce.admin.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.phoenix.ecommerce.admin.AdminViewModel
import com.phoenix.ecommerce.customers.products.ProductDetail
import com.phoenix.ecommerce.customers.products.ProductImageView
import com.phoenix.ecommerce.customers.products.ProductsInfo
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminEditEachProductScreen(sharedViewModel: SharedViewModel, products: Products, navController: NavController){

    val viewModel : AdminViewModel = viewModel()
    val context = LocalContext.current
    val onDeal = remember{ mutableStateOf(false) }

    viewModel.checkIfItemOnDeal(products){
        if(it){
            onDeal.value = true
        }
        else{
            onDeal.value = false

        }
    }
    var priceChangeDialogueState by remember {
        mutableStateOf(false)
    }
    var menuOptionState by remember {
        mutableStateOf(false)
    }

    var stockCountDialogueState by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { 
                        navController.navigateUp()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "Edit Product")
                        IconButton(onClick = {
                            menuOptionState = true

                        }) {
                            DropdownMenu(expanded = menuOptionState, onDismissRequest = {
                                menuOptionState = false
                            }) {

                                TextButton(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        menuOptionState = false
                                        stockCountDialogueState = true
                                    }) {
                                    Text(
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(8.dp),
                                        text = "Update Stock "
                                    )
                                }
                                TextButton(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        sharedViewModel.addProduct(products)
                                        navController.navigate(Routes.ADD_IMAGES_SCREEN)
                                    }) {
                                    Text(
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(8.dp),
                                        text = "Add Images "
                                    )
                                }
                                TextButton(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier.fillMaxWidth(), onClick = {
                                        viewModel.removeProduct(products){
                                            if(it){
                                                navController.navigateUp()
                                                Toast.makeText(context, "Product Removed Successfully", Toast.LENGTH_SHORT)
                                                    .show()
                                            }
                                        }

                                    }) {
                                    Text(
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(8.dp),
                                        text = "Remove product "
                                    )
                                }




                            }
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                    }
            })
        },
        bottomBar = {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 30.dp)
                ) {
                    if(onDeal.value){
                    Button(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        onClick = {
                            viewModel.removeItemsFromDeals(products)
                            Toast.makeText(context, "Removed from current deals", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()

                        }) {
                        Text(text = "Remove from Deals")

                    }}
                    else{Button(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        onClick = {
                            viewModel.addProductToSpotlight(products)
                            Toast.makeText(context, "Product added to Deals", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()

                        }) {
                        Text(text = "Add product to Deals")

                    }

                    }
                    
                    OutlinedButton(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        onClick = {
                            priceChangeDialogueState = true
                    }) {
                        Text(text = "Decrease current price")
                    }
                    

            }
        },
        content = {
                innerPadding->

            if(stockCountDialogueState) {
                addStockCountDialogue(navController, products, viewModel) {
                    stockCountDialogueState = it
                    viewModel.getAllReceivedOrders()
                }
            }

            if(priceChangeDialogueState) Dialog(
                onDismissRequest = {
                    priceChangeDialogueState = false
                }) {
                var changedPrice by remember { mutableStateOf("") }
                Box(modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxWidth()
                    .padding(16.dp)
                ){
                    Column {
                        Text(
                            modifier = Modifier.padding(bottom = 16.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Add new amount")
                        TextField(
                            placeholder = {
                                Text(text = "Add amount here")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            trailingIcon = {
                                IconButton(onClick = {
                                    viewModel.priceChange(products, changedPrice.toFloat())
                                    products.discountedPrice = changedPrice.toFloat()
                                    priceChangeDialogueState= false

                                }) {
                                    Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "Set")
                                }
                            },
                            value = changedPrice , onValueChange = {changedPrice = it})
                    }

                }
            }

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