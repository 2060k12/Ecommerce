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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.TableInfo
import com.google.android.play.integrity.internal.i
import com.phoenix.ecommerce.data.data.product.Products
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductSecondScreen(navController: NavController, productName: String, productCost: String, productCategory: String, productInfo: String, imageLink : String?){

    // initializing the viewmodel
    val viewModel : AdminViewModel = viewModel()

    // initially the color picker dialogue is hidden
    var colorDialogPickerState by remember {
        mutableStateOf(false)
    }
    val pickedColors  = remember {
        mutableStateListOf<String>()}

    val productSpec = remember {
        mutableStateOf("")
    }
    val productSpecsOptions =  remember {
        mutableStateListOf<String>()
    }
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
                    onClick = {
                        val product = Products(
                            productCategory = productCategory,
                            productName = productName,
                            productId = UUID.randomUUID().toString(),
                            productCost = productCost.toInt(),
                            productIconUrl = imageLink.toString(),
                            productInfo =productInfo,
                            productColor = pickedColors.toList(),
                            productSpecs = productSpecsOptions
                        )
                        viewModel.addNewProduct(product)



                    }) {
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
                            onDismissRequest = {
                                colorDialogPickerState = false
                                pickedColors.clear()
                            }) {

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
                                            colorDialogPickerState = false
                                        }) {
                                        Text(text = "Done")


                                    }

                                }

                            }
                        }

                    }
                }



                Column {

                    Card(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .height(90.dp)
                            .padding(16.dp)
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .clickable {
                                colorDialogPickerState = true
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .wrapContentHeight()
                                .fillMaxSize(),
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



                    // Add Specs
                    OutlinedTextField(
                        label = {
                            Text(text = "Add Product Specs Options")
                        },
                        trailingIcon = {
                            Button(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier.padding(horizontal = 8.dp),
                                onClick = {
                                    if(productSpec.value.isNotEmpty()) {
                                        productSpecsOptions.add(productSpec.value)
                                        productSpec.value = ""
                                    }
                                }) {
                                Text(text = "Done")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        value = productSpec.value, onValueChange = {
                            productSpec.value = it
                        } )


                    Card(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        if(pickedColors.isNotEmpty()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 2.dp
                                    ),
                                    text = "Picked Colors"
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            pickedColors.clear()
                                        },
                                    text = "Clear")

                                    }

                            }


                            for (i in pickedColors) {
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 2.dp
                                    ),
                                    text = "${pickedColors.indexOf(i) + 1}. $i"
                                )

                            }
                        }


                    Card(
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        if (productSpecsOptions.isNotEmpty()) {


                            Row(modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 2.dp
                                    ),
                                    text = "Product Specs"
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .clickable {
                                            productSpecsOptions.clear()
                                        },
                                    text = "Clear")

                            }




                            for (i in productSpecsOptions) {
                                Text(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 2.dp
                                    ),
                                    text = "${productSpecsOptions.indexOf(i) + 1}. $i"
                                )

                            }
                        }
                    }

                }
            }
                }




}