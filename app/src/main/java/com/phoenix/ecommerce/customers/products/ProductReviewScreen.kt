package com.phoenix.ecommerce.customers.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.utils.ReviewStars
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductReviewScreen(navController: NavController,sharedViewModel: SharedViewModel) {

    val products = sharedViewModel.product

    Scaffold (

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
                Text(text = products.productName)
            })
        },
        bottomBar = {
            OutlinedTextField(
                trailingIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = "" )
                    }
                },
                placeholder = {
                    Text(text = "Enter your review here")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp, start = 16.dp, end = 16.dp),
                value = "", onValueChange = {})
        }
        
    ){
        Surface(modifier = Modifier.padding(it)) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        text = "Reviews"
                    )
                    ReviewStars(color = Color(0xffffa534), numberOfStars = 4)
                }


                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(vertical = 16.dp),

                    ) {
                    items(10) {
                       EachReview()
                    }
                }
                
            }

        }
    }
}

@Composable
fun EachReview(){
    Card (
        modifier = Modifier.fillMaxWidth()
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                // TODO: image of user
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .wrapContentSize()
                            // TODO: remove red
                            .background(Color.Red)
                    ) {
                        AsyncImage(
                            modifier = Modifier.size(40.dp),
                            model = "", contentDescription = "Profile Image"
                        )
                    }
                    Column {
                        Text(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Pranish Pathak"
                        )
                        ReviewStars(color = Color(0xffffa534), numberOfStars = 3)
                    }
                }

            }

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Hello WOrld")



        }

    }
}