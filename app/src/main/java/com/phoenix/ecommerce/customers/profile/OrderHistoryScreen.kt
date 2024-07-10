package com.phoenix.ecommerce.customers.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.customers.products.ProductsViewModel
import com.phoenix.ecommerce.data.data.AdminReceivedOrder
import com.phoenix.ecommerce.utils.BottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(navController: NavController) {

    val viewModel : ProductsViewModel = viewModel()

    // all ordered product by current user
    val orderedList = viewModel.orderedProductList.observeAsState().value
    viewModel.getALlOrderedList()

    val processingOrders = ArrayList<AdminReceivedOrder>()
    val newOrders = ArrayList<AdminReceivedOrder>()
    val doneOrders = ArrayList<AdminReceivedOrder>()
    val refunds = ArrayList<AdminReceivedOrder>()
    var selected = orderedList

    if (orderedList != null) {
        for (order in orderedList){
            when(order.status.lowercase()) {
                "new" -> newOrders.add(order)
                "processing" -> processingOrders.add(order)
                "done" -> doneOrders.add(order)
                "refund" -> refunds.add(order)
            }
        }
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
                Text(text = "Order History")
            })
        },
        bottomBar = {
        }
    ) {
        Surface(modifier = Modifier.padding(it)) {

            val selectedFilter = remember {
                mutableStateOf("")
            }

            Column() {

                Row (
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(8.dp)
                ){

                    FilterChip(
                  modifier = Modifier
                      .weight(1f),
                        selected = if(selectedFilter.value == "new") true else false,
                        onClick = {
                            selectedFilter.value = "new"
                            selected = newOrders
                        },
                        label = {
                            Text(text = "New")
                        })
                    FilterChip(
                        modifier = Modifier
                            .weight(1f),
                        selected = if(selectedFilter.value == "processing") true else false,
                        onClick = {
                            selectedFilter.value = "processing"
                            selected = processingOrders

                        },
                        label = {
                            Text(text = "Confirm")
                        })

                    FilterChip(
                        modifier = Modifier
                            .weight(1f),
                        selected = if(selectedFilter.value == "done") true else false,
                        onClick = {
                            selectedFilter.value = "done"
                            selected = doneOrders

                        },
                        label = {
                            Text(text = "Done")
                        })
                    FilterChip(
                        modifier = Modifier
                            .weight(1f),
                        selected = if(selectedFilter.value == "refund") true else false,
                        onClick = {
                            selectedFilter.value = "refund"
                            selected = refunds
                        },
                        label = {
                            Text(text = "Refund")
                        })

                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {


                    if (selected != null) {
                        items(selected!!) { item ->

                            val statusColor = remember {
                                mutableStateOf(Color.Gray)
                            }

                            when (item.status.lowercase()) {
                                "done" -> statusColor.value = Color.Gray
                                "processing" -> statusColor.value = Color.Blue
                                "new" -> statusColor.value = Color.Magenta
                                "refund" -> statusColor.value = Color.Green

                            }

                            Card(
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, statusColor.value, RoundedCornerShape(20.dp))


                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                                    modifier = Modifier.fillMaxSize()
                                        .padding(16.dp)
                                ) {
                                    AsyncImage(
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(70.dp)
                                            .clip(RoundedCornerShape(10.dp)),
                                        model = item.products.productIconUrl,
                                        contentDescription = "Image"
                                    )
                                    Column(
                                        modifier = Modifier.fillMaxSize()

                                    ) {
                                        Text(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            text = item.products.productName
                                        )
                                        Text(text = "AU$ " + item.products.productCost.toString())

                                        Text(text = item.status)

                                    }
                                }
                            }
                        }
                    } else {
                        item {

                            Text(text = "No Products to show")

                        }
                    }
                }
            }

        }
    }
}