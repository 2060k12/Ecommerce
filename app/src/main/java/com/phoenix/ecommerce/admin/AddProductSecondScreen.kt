package com.phoenix.ecommerce.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.sharp.AccountBox
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.room.util.TableInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductSecondScreen(navController: NavController, productName: String, productCost: String, productCategory: String, productInfo: String, imageLink : String?){

    // initially the color picker dialogue is hidden
    var colorDialogPickerState by remember {
        mutableStateOf(false)
    }
    val pickedColors : ArrayList<String> = ArrayList()
    
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
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Add Product")
                }
                OutlinedButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")

                }
            }

        }
    ){
            Surface(
                modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (colorDialogPickerState) {
                        Dialog(
                            onDismissRequest = { colorDialogPickerState = false }) {

                            Column(

                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(16.dp)

                            ) {
                                Text(text = "Choose Colors")


                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(3),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    item {
                                        EachColorPickerCard(Color.Red, "Red") {
                                            pickedColors.add("Red")
                                        }
                                    }
                                    item {
                                        EachColorPickerCard(Color.Blue, "Blue") {
                                            pickedColors.add("Blue")

                                        }
                                    }
                                    item {
                                        EachColorPickerCard(Color.Green, "Green") {
                                            pickedColors.add("Green")

                                        }
                                    }
                                    item {
                                        EachColorPickerCard(Color.Black, "Black") {
                                            pickedColors.add("Black")

                                        }
                                    }
                                    item {
                                        EachColorPickerCard(Color.White, "White") {
                                            pickedColors.add("White")

                                        }
                                    }
                                    item {
                                        EachColorPickerCard(Color.Gray, "Gray") {
                                            pickedColors.add("Gray")

                                        }
                                    }


                                }
                                Row (
                                    modifier = Modifier.fillMaxWidth()){

                                    Button(
                                        shape = RoundedCornerShape(5.dp),
                                        modifier = Modifier
                                            .padding(0.dp)
                                            .fillMaxWidth(),
                                        onClick = {
                                        }) {
                                        Text(text = "Done")


                                    }

                                }

                            }
                        }

                    }
                }


                Card(modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .clickable {
                        colorDialogPickerState = true
                    }
                ) {
                    Column {
                        Text(text = "Add Color Options")
                    }

                }


                Card(modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .clickable {
                        colorDialogPickerState = true
                    }
                ) {
                    Row(
                        modifier = Modifier.wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Add Color Options")
                        Icon(
                            modifier = Modifier.size(50.dp),
                            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "")
                    }
                }
            }
                }


}