package com.phoenix.ecommerce.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductSecondScreen(navController: NavController, productName: String, productCost: String, productCategory: String, productInfo: String, imageLink : String?){

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()

                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "back")
                    }

                },
                title = {
                Text(text = "Continue")
            })
        },

        bottomBar = {


            Column(
                modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp)
            ) {
                Button(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Add Product")
                }
                OutlinedButton(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")

                }
            }

        }
    ){
            Surface(modifier = Modifier.padding(it)) {
                Text(text = "$productName, $productCost, $productCategory, $productInfo")
                Text(text = imageLink!!)

            }

    }
}