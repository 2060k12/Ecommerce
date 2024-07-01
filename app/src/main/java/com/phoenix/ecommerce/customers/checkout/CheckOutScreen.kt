package com.phoenix.ecommerce.customers.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phoenix.ecommerce.customers.cart.CartViewModel
import com.phoenix.ecommerce.customers.profile.ProfileViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.utils.SharedViewModel
import kotlinx.serialization.json.JsonNull.content

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOutScreen(sharedViewModel: SharedViewModel){
    //  checkout ViewModel
    val viewModel :CheckOutViewModel = viewModel()

    // cart viewModel
    val cartViewModel :CartViewModel = viewModel()

    // userProfileViewModel
    val profileViewModel : ProfileViewModel = viewModel()

    val listOfCartProducts : List<Products> = sharedViewModel.listOfProducts
    var userDeliveryAddress by remember { mutableStateOf("") }

    // add new address dialogue box state
    var newAddressDialogueBox by remember {
        mutableStateOf(false)
    }

    var currentAddressCheckedState by remember {
        mutableStateOf(false)
    }
    var newAddressChecked by remember {
        mutableStateOf(false)
    }
    if(currentAddressCheckedState){
        userDeliveryAddress = profileViewModel.currentUsersProfile.value.address
    }


    profileViewModel.getCurrentUserProfile()


    Scaffold(

        bottomBar = {
            Column(modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp)) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {

                        viewModel.checkOut(listOfCartProducts, userDeliveryAddress){
                            success->
                            if(success){
                                cartViewModel.clear()
                            }
                        }
                    }) {
                    Text(text = "Confirm Order")

                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")

                }
            }

        },

        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(text = "CheckOut")
                })


        }

    ) {innerPadding->

        Surface(modifier = Modifier.padding(innerPadding)) {


            Column {

                // for current address in user's account
                Box(modifier = Modifier
                    .wrapContentSize()
                    ){
                CheckOutAddress("Home", "20 albert Parade,\nAshfield, 2131\nNSW"){
                    currentAddressCheckedState = it
                    newAddressChecked = false
                }}

                // to add new account
                Box(modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        newAddressDialogueBox = true

                    }){
                CheckOutAddress(){
                    currentAddressCheckedState = false
                    newAddressChecked = it
                    newAddressDialogueBox = true
                }}


                if(newAddressDialogueBox && newAddressChecked){
                Dialog(
                    onDismissRequest = {
                        newAddressChecked = false
                        userDeliveryAddress = ""
                        newAddressDialogueBox = false

                }) {
                    Surface(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                text = "New Address"
                            )
                            OutlinedTextField(
                                modifier = Modifier.padding(vertical = 16.dp),
                                placeholder = {
                                    Text(text = "Enter Address")
                                },
                                value = userDeliveryAddress, onValueChange = {
                                    userDeliveryAddress = it
                                })
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedButton(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(end = 8.dp),
                                    onClick = {
                                        newAddressDialogueBox = false
                                    }) {
                                    Text(text = "Cancel")
                                }
                                Button(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(start = 8.dp),
                                    onClick = {
                                        newAddressDialogueBox = false
                                    }) {
                                    Text(text = "Add")

                                }
                            }
                        }
                    }

                }
                }
                Text(text = userDeliveryAddress)






            }
            CheckOutPayment()
        }
    }
}


@Composable
fun CheckOutScreenPreview(){
    CheckOutScreen(sharedViewModel = SharedViewModel())
}