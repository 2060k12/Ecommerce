package com.phoenix.ecommerce.admin.ui

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    var selectedImages = remember {
        listOf<Uri>()
    }

    val context = LocalContext.current
    //  Launch our gallery to pick images
    val launch = openGallery(){
        selectedImages = it

    }
    //todo
    // all featured images of a product
    val productImageList =viewModel.productImageList.observeAsState().value
    val product = sharedViewModel.product
    viewModel.getFeaturedImages(product)
    val galleryState = remember {
        mutableStateOf(false)
    }

    val imageList = ArrayList<Image>()


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
                        for (img in selectedImages){
                            imageList.add(Image(img.toString()))
                        }
                        viewModel.setFeaturedImages(product , imageList)

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

                   if(selectedImages.isNotEmpty()){

                       item {
                           ProductImagePagerView(listOfImages = imageList)
                       }
                   }

                   item {
                       Card(
                           onClick = {
                               galleryState.value = true
                           },
                           modifier = Modifier
                               .fillMaxWidth()
                               .padding(16.dp)
                               .height(300.dp)
                       ) {

                       }
                       if (galleryState.value) {
                           launch()
                       }
                   }
               }
    }
}


@Composable
fun openGallery(callBack : (images : List<Uri> ) -> Unit) :(() -> Unit ){
    // open gallery
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {
            callBack(it)
        }

    )
    return { launcher.launch("image/*")}

}