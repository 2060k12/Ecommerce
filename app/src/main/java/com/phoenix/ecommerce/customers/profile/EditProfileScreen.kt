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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.phoenix.ecommerce.utils.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(sharedViewModel: SharedViewModel) {

    val name = sharedViewModel.profileInfo.name
    val imageLink = sharedViewModel.profileInfo.profileImage

    val nameText = remember {
        mutableStateOf(name)
    }

    Scaffold(
        topBar = {
            TopAppBar(

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
                    onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
                OutlinedButton(
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp
                        )
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
            }
        }

    ) {
        innerPadding->
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
            Card (
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
                        modifier = Modifier.fillMaxSize(),
                        model = imageLink, contentDescription = "")


                    Row(
                        modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 30.dp)
                            .clickable {
                                println("Hello World")
                            },
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
            )



        }
    }
}