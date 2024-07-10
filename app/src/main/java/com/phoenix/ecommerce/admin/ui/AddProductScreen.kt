package com.phoenix.ecommerce.admin.ui

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.navigation.RoutesAdmin
import com.phoenix.ecommerce.utils.AdminNavigationBar
import com.phoenix.ecommerce.utils.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController){

    val sharedViewModel : SharedViewModel = viewModel()


    // link for our image
    var imageLink by remember {
        mutableStateOf<Uri?>(null)
    }
    //  Launch our gallery to pick images
    val launch = galleryLauncher {
        imageLink = it
    }
    // Name of the product
    var productName by remember {
        mutableStateOf("")
    }
    // product Info
    var productInfo by remember {
        mutableStateOf("")
    }
    // Cost of product
    var productCost by remember {
        mutableStateOf("")
    }

    // Category for the product
    var productCategory by remember {
        mutableStateOf("")
    }


    // state for drop down
    var dropDownState by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Scaffold(
        
        // Top bar
        topBar = {
            TopAppBar(title = { 
                Text(text = "Add New Product")
            })
            
        },
        
        // bottom bar
        bottomBar = {
            AdminNavigationBar(navController = navController, "add")
        }
        
    ) {
        innerPadding->
        Surface(modifier = Modifier.padding(innerPadding)) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Card(modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .clickable {
                        launch()
                        // when add image is clicked


                    }
                ) {

                    if(imageLink== null) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                            modifier =
                            Modifier
                                .padding(16.dp)
                                .fillMaxSize()){


                            Icon(
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
,                                imageVector = Icons.Default.Add, contentDescription = "add")
                            Text(
                                fontWeight = FontWeight.Bold,
                                text = "Click To add Image")
                        }

                    }
                    else{
                        AsyncImage(
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize(),
                            model = imageLink, contentDescription = ""
                        )
                    }
                }


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Product Name")
                    },
                    value = productName , onValueChange = {
                    productName = it
                })
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                    ,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    label = {
                        Text(text = "Product Cost")
                    },
                    value = productCost , onValueChange = {
                        productCost = it
                    })
                OutlinedTextField(
                    minLines = 5,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Product Info")
                    },
                    value = productInfo , onValueChange = {
                        productInfo = it
                    })


                val dropDownButtonText = remember {
                    mutableStateOf("Select Categories")
                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { dropDownState = true }) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = dropDownButtonText.value)
                        Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Down")
                    }

                    DropdownMenu(
                        modifier = Modifier
                            .padding(horizontal = 50.dp),
                        expanded = dropDownState, onDismissRequest = { dropDownState= !dropDownState }) {
                        // Drop down menu item "Computer"
                        DropdownMenuItem(text = { Text(text = "Computer")},
                            onClick = {
                                dropDownButtonText.value = "Category: Computer"
                                productCategory = "computer"
                                dropDownState = false
                        })
                        // Drop down menu item "Mobile"
                        DropdownMenuItem(text = { Text(text = "Mobile Phones")},
                            onClick = {
                                dropDownButtonText.value = "Category: Mobile"
                                productCategory = "mobile"
                                dropDownState = false

                        })
                        // Drop down menu item "Watch"
                        DropdownMenuItem(text = { Text(text = "Watches")},
                            onClick = {
                                dropDownButtonText.value = "Category: Watch"
                                productCategory = "watch"
                                dropDownState = false
                        })

                    }
                }


                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        // takes to next page
                        if(productCost.isNotEmpty() && productName.isNotEmpty() && productCategory.isNotEmpty() && productInfo.isNotEmpty()) {

                            navController.navigate(RoutesAdmin.AdminAddScreenTwo(
                                productName =  productName,
                                productCost = productCost,
                                productCategory = productCategory,
                                imageLink = imageLink.toString(),
                                productInfo = productInfo
                            ))

                        }
                        else{
                            Toast.makeText(context, "Please fill Everything before continuing", Toast.LENGTH_SHORT).show()
                        }
                        
                    }) {
                    Text(text = "Next")
                }

                OutlinedButton(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")

                }

            }
        }
        
    }

}


@Preview()
@Composable
fun AddProductScreenPreview(){
    AddProductScreen(rememberNavController())
}
