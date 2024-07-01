package com.phoenix.ecommerce.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.play.integrity.internal.o
import com.phoenix.ecommerce.data.data.AdminReceivedOrder
import com.phoenix.ecommerce.utils.AdminNavigationBar
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController){
    // Admin ViewModel
    val viewModel : AdminViewModel= viewModel()
    // livedata
    val listOfReceivedOrder = viewModel.listOfReceivedOrder.observeAsState(ArrayList()).value
    val completedOrdersList = viewModel.completedOrdersList.observeAsState(ArrayList()).value
    viewModel.getAllReceivedOrders()

Scaffold(

    // top bar
    topBar = {
        TopAppBar(title = {
            Text(text = "Admin Panel")
        })
    },

    // bottom bar
    bottomBar = {
        AdminNavigationBar(navController)
    }

) {
    Surface(modifier = Modifier.padding(it)) {
        Column(
            modifier = Modifier
                .padding(16.dp)
//                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {

            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "New Orders"
            )

            LazyColumn {

                items(listOfReceivedOrder){
                    item->
                    ReceivedOrders(item)
                }

            }

            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Completed Orders"
            )

            LazyColumn {

                items(completedOrdersList){
                        item->
                    ReceivedOrders(item)
                }

            }

        }
    }
    
}

}

@Composable
fun ReceivedOrders(receivedOrder: AdminReceivedOrder) {
    Card(modifier = Modifier
        .padding(vertical = 8.dp)
        .fillMaxWidth()
        .height(100.dp)
        ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp)
        ) {
            Row {
                Card(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(),
                    shape = RoundedCornerShape(5.dp),
                ) {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        model = receivedOrder.products.productIconUrl,
                        contentDescription = ""
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = receivedOrder.products.productName
                    )
                    Text(
                        fontWeight = FontWeight.Bold,
                        text = receivedOrder.products.productCategory)
                    Text(text = "Received: ${receivedOrder.orderPlacedOn.toInstant().toString().dropLast(14)} ")


                }
            }
                Text(
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 16.dp),
                    text = "AU$ ${receivedOrder.products.productCost.toString()}")

        }
        }


}


@Preview(showSystemUi = true)
@Composable
fun AdminScreenPreview(){
    AdminScreen(rememberNavController())

}