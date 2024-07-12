package com.phoenix.ecommerce.customers.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.admin.ui.galleryLauncher
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.IndeterminateCircularIndicator
import com.phoenix.ecommerce.utils.SharedViewModel
import kotlinx.coroutines.launch
import okhttp3.Route


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val name = sharedViewModel.profileInfo.name
    val phone = sharedViewModel.profileInfo.phone
    val address = sharedViewModel.profileInfo.address
    val imageLink = sharedViewModel.profileInfo.profileImage
    var imgLink = remember {
        mutableStateOf(imageLink)
    }

    val viewModel : ProfileViewModel = viewModel()

    val nameText = remember {
        mutableStateOf(name)
    }
    val addressText = remember {
        mutableStateOf(address)
    }
    val phoneText = remember {
        mutableStateOf(phone)
    }
    val img = remember {
        mutableStateOf(imageLink)
    }
    val snackBarScope = rememberCoroutineScope()

    val launcher = galleryLauncher {
        img.value = it.toString()
        imgLink.value = it.toString()
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
                Text(text = "Edit Profile ")

            })
        },
        bottomBar = {
            Column(
                modifier = Modifier.padding(vertical = 32.dp)
            ){
                Button(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    onClick = {
                        when(sharedViewModel.editDetail){

                            "nameAndImage" -> {
                                viewModel.editProfileNameImage(
                                    sharedViewModel.profileInfo,
                                    sharedViewModel.editDetail,
                                    nameText.value,
                                    img.value
                                )
                                navController.navigate(Routes.PROFILE_SCREEN)
                            }

                            "phone" -> {
                                viewModel.editProfileInformation(
                                    sharedViewModel.profileInfo,
                                    sharedViewModel.editDetail,
                                    editedInfo = phoneText.value
                                )
                                navController.navigate(Routes.PROFILE_SCREEN)
                            }
                            "address" -> {
                                viewModel.editProfileInformation(
                                    sharedViewModel.profileInfo,
                                    sharedViewModel.editDetail,
                                    editedInfo = addressText.value
                                )
                                navController.navigate(Routes.PROFILE_SCREEN)
                            }
                        }

                    }) {
                    Text(text = "Save")
                }
                OutlinedButton(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp
                        )
                        .fillMaxWidth(),
                    onClick = {
                        navController.navigateUp()

                    }) {
                    Text(text = "Cancel")
                }
            }
        }

    ) {
        innerPadding->
        when (sharedViewModel.editDetail){

            "nameAndImage" ->
                Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()) {
                Card(
                    onClick = launcher,
                    shape = CircleShape,
                    modifier = Modifier.size(200.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    ) {

                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            model = imgLink.value, contentDescription = ""
                        )


                        Row(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Create,
                                contentDescription = "Add image"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Edit")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "")
                    },
                    singleLine = true,
                    value = nameText.value, onValueChange = {
                        nameText.value = it
                    }
                ) }

            "phone" ->
                Column(

                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)){
                    OutlinedTextField(
                        label = {
                            Text(text = "Phone Number")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                        },
                        singleLine = true,
                        value = phoneText.value, onValueChange = {
                            phoneText.value = it
                        }
                    ) }

            "address" ->
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)) {

                    OutlinedTextField(
                        label = {
                            Text(text = "Address")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
                        },
                        singleLine = true,
                        value = addressText.value, onValueChange = {
                            addressText.value = it
                        }
                    ) }
        }

                }



        }
