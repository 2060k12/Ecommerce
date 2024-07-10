package com.phoenix.ecommerce.customers.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.play.core.integrity.z
import com.phoenix.ecommerce.R
import com.phoenix.ecommerce.data.data.profile.Profile
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.SharedViewModel


@Composable
fun ProfileUserName(profile : Profile, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(16.dp))
    {
        Row(modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxSize()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){


                AsyncImage(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    model = profile.profileImage , contentDescription = "Profile Image")


                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    text =profile.name)
            }


            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "Edit Profile")
        }



    }
}

@Composable
fun ProfileGeneral(profile : Profile, navController: NavController, sharedViewModel: SharedViewModel){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(modifier = Modifier.padding(top =  16.dp, start = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "General")


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                Row(

                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 4.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Email, contentDescription = "Profile"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = profile.email)
                    }
                    Icon(modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Lock, contentDescription = "Profile")
                }
            }


            Card(
                onClick = {
                    sharedViewModel.addEditDetails("phone")
                    sharedViewModel.addProfileInfo(profile)
                    navController.navigate(Routes.EDIT_PROFILE_SCREEN)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp,
                        vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Phone, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = profile.phone)
                }
            }



            Card(
                onClick = {
                    sharedViewModel.addEditDetails("address")
                    sharedViewModel.addProfileInfo(profile)
                    navController.navigate(Routes.EDIT_PROFILE_SCREEN)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)) {

                Row(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.LocationOn, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = profile.address)
                }
            }

            Card(
                onClick = {
                    navController.navigate(Routes.ORDERS_HISTORY_SCREEN)
                },
                modifier = Modifier
                .fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.ShoppingCart, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Order History")
                }
            }
        }
    }
}

@Composable
fun ProfileNotification(onClick: () -> Unit){

    val viewModel :ProfileViewModel = viewModel()
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Privacy & Security")


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Push Notification")

                }
            }


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                Row(

                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Profile"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Password")
                    }

                    TextButton(
                        shape = RoundedCornerShape(5.dp),
                        onClick = {
                            viewModel.resetPassword()
                            onClick()


                        }) {
                        Text(text = "Reset")

                    }
                }
            }


            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Info, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Feedback")
                }
            }
        }
    }
}


@Composable
fun ProfileVersion(){
    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            fontSize = 12.sp,
            text = "Version 1.0.0")
        Text(
            fontSize = 12.sp,
            text = "Copyright \u00A9 2024, all rights reserved")
    }
}

@Composable
fun SignOutFeature(onClick : ()->Unit){
    Button(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            // SignOut
            onClick()
        }) {

        Text(text = "Sign out")

    }
    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        onClick = {
            // Delete Account
            // TODO: Delete account implementation
        }) {

        Text(text = "Delete Account")

    }

}