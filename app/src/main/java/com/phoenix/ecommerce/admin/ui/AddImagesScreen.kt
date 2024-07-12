package com.phoenix.ecommerce.admin.ui

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.play.integrity.internal.i
import com.phoenix.ecommerce.customers.products.ProductImagePagerView
import com.phoenix.ecommerce.customers.products.ProductsViewModel
import com.phoenix.ecommerce.data.data.product.Image
import com.phoenix.ecommerce.utils.SharedViewModel
import java.net.URI
import java.util.Collections.addAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddImagesScreen(sharedViewModel: SharedViewModel, navController: NavController){
    val viewModel : ProductsViewModel = viewModel()
    val selectedImages =  remember {
        mutableListOf<Uri>()
    }
    val imageList = ArrayList<Image>()
    val context = LocalContext.current
    var imageSelected by remember {
        mutableStateOf(false)
    }
    // Launch our gallery to pick images
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        selectedImages.addAll(uris)
        if(selectedImages.isNotEmpty()) {
            imageSelected = true
            for (i in selectedImages){
                imageList.add(Image(i.toString()))
            }
        }
    }

    val productImageList =viewModel.productImageList.observeAsState().value
    val product = sharedViewModel.product
    viewModel.getFeaturedImages(product)
    val galleryState = remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                title = { Text(text = "Add Images") })
        },

        bottomBar = {
            Column (
                modifier = Modifier.padding(bottom = 30.dp)
            ){

                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        if(selectedImages.isNotEmpty()) {
                            viewModel.setFeaturedImages(product, imageList)
                        }
                        else{
                            Toast.makeText(context, "Please select image first", Toast.LENGTH_SHORT).show()
                        }
                    }) {

                    Text(text = "Add Images")
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

    ) {
               LazyColumn (
                   modifier = Modifier.padding(it)
               ) {

                   if(!imageSelected)
                       item {
                           Card(
                               onClick = {
                                  launcher.launch("image/*")
                               },
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(16.dp)
                                   .height(200.dp)
                           ) {
                               Column (
                                   modifier = Modifier.fillMaxSize(),
                                   verticalArrangement = Arrangement.Center,
                                   horizontalAlignment = Alignment.CenterHorizontally
                               ){
                                   Icon(
                                       modifier = Modifier.size(50.dp),
                                       imageVector = Icons.Default.AddCircle, contentDescription = "add")
                               }

                           }


                   }
                   if(imageSelected){
                       item(imageList){
                           ProductImagePagerView(listOfImages = imageList)
                       }
                   }


               }
    }
}
