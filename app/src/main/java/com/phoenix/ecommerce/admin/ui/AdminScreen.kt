package com.phoenix.ecommerce.admin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.phoenix.ecommerce.admin.AdminViewModel
import com.phoenix.ecommerce.utils.AdminNavigationBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController) {
    // tabIndex
    var tabIndex by remember {
        mutableIntStateOf(0)
    }

    var snackBarHostState = remember {
        SnackbarHostState()
    }
    var snackBarScope = rememberCoroutineScope()

    // Admin ViewModel
    val viewModel: AdminViewModel = viewModel()
    // livedata
    val listOfReceivedOrder = viewModel.listOfReceivedOrder.observeAsState(ArrayList()).value
    val completedOrdersList = viewModel.completedOrdersList.observeAsState(ArrayList()).value
    val processingOrdersList = viewModel.processingOrdersList.observeAsState(ArrayList()).value
    val refundList = viewModel.refundList.observeAsState(ArrayList()).value
    viewModel.getAllReceivedOrders()

    Scaffold(

        snackbarHost = {
          SnackbarHost(hostState = snackBarHostState)
        },
        // top bar
        topBar = {
            TopAppBar(title = {
                Text(text = "Admin Panel")
            })
        },

        // bottom bar
        bottomBar = {
            AdminNavigationBar(navController,"dashboard")
        }

    ) {
        Surface(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
//                .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                TabRow(

                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .fillMaxWidth(),
                    selectedTabIndex = tabIndex
                ) {

                    Tab(selected = tabIndex == 0, onClick = {
                        tabIndex = 0
                    }) {
                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "New "
                        )
                    }

                    Tab(selected = tabIndex == 1, onClick = {
                        tabIndex = 1
                    }) {
                        Text(

                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Confirm"
                        )
                    }

                    Tab(selected = tabIndex == 2, onClick = {
                        tabIndex = 2
                    }) {

                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Completed "
                        )
                    }
                    Tab(selected = tabIndex == 3, onClick = {
                        tabIndex = 3
                    }) {

                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Refund "
                        )
                    }

                }




                LazyColumn {

                    when (tabIndex) {
                        0 ->
                            items(listOfReceivedOrder) { item ->
                                viewModel.getAllReceivedOrders()
                                ReceivedOrders(item) {
                                    viewModel.addProductsAsProcessing(item)
                                    snackBarScope.launch {
                                        snackBarHostState.showSnackbar("Processing item")
                                    }
                                    viewModel.getAllReceivedOrders()
                                }
                            }
                        
                        
                        
                        


                        1 -> items(processingOrdersList) { item ->
                            viewModel.getAllReceivedOrders()
                            ProcessingOrders(item){
                                viewModel.addProductAsCompleted(item)
                                snackBarScope.launch {
                                    snackBarHostState.showSnackbar("Marked as Done")
                                }
                                viewModel.getAllReceivedOrders()

                            }
                        }


                        2 -> items(completedOrdersList) { item ->
                            viewModel.getAllReceivedOrders()
                            CompletedOrders(item){
                                viewModel.refundProduct(item){

                                    snackBarScope.launch {
                                        snackBarHostState.showSnackbar("Product Successfully refunded")
                                    }

                                    viewModel.getAllReceivedOrders()
                                }
                            }
                        }

                        3 -> items(refundList) { item ->
                            viewModel.getAllReceivedOrders()
                            RefundedOrders(item)
                        }



                    }


                }


            }
        }

    }

}
