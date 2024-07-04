package com.phoenix.ecommerce.admin

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    // tabIndex
    var tabIndex by remember {
        mutableIntStateOf(0)
    }

    // Admin ViewModel
    val viewModel : AdminViewModel= viewModel()
    // livedata
    val listOfReceivedOrder = viewModel.listOfReceivedOrder.observeAsState(ArrayList()).value
    val completedOrdersList = viewModel.completedOrdersList.observeAsState(ArrayList()).value
    val processingOrdersList = viewModel.processingOrdersList.observeAsState(ArrayList()).value
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

            TabRow(

                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxWidth(),
                selectedTabIndex = tabIndex) {

                Tab(selected = tabIndex == 0 , onClick = {
                    tabIndex = 0
                }) {
                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "New "
                    )
                }

                Tab(selected = tabIndex == 1, onClick = {
                    tabIndex = 1
                }) {

                    Text(

                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Confirmed"
                    )
                }

                Tab(selected = tabIndex == 2 , onClick = {
                    tabIndex = 2
                }) {

                    Text(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Completed "
                    )
                }

            }




            LazyColumn {

                when(tabIndex){
                    0  -> {
                        items(listOfReceivedOrder){
                                item->
                            ReceivedOrders(item)
                        }
                    }

                    1-> {
                        items(processingOrdersList){
                                item->
                            ReceivedOrders(item)
                        }

                    }
                    2-> {
                        items(completedOrdersList){
                                item->
                            ReceivedOrders(item)
                        }

                    }


                }


            }


        }
    }
    
}

}



@Preview(showSystemUi = true)
@Composable
fun AdminScreenPreview(){
    AdminScreen(rememberNavController())

}