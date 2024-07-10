package com.phoenix.ecommerce.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.ecommerce.admin.AdminViewModel
import com.phoenix.ecommerce.customers.products.ProductsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefundScreen(){


    val viewModel : ProductsViewModel = viewModel()
    val refundList = viewModel.refundList.observeAsState().value
    viewModel.getALlOrderedList()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Refunded Items")
            })
        }

    ) {
        Box(modifier = Modifier.padding(it)){
            LazyColumn {


            }
        }

    }
}