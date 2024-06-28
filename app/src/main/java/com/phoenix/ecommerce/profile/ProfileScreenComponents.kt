package com.phoenix.ecommerce.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoenix.ecommerce.R


@Composable
fun ProfileUserName(onClick: () -> Unit){
    Card(modifier = Modifier
        .clickable {
            onClick()
        }
        .fillMaxWidth()
        .height(120.dp)
        .padding(16.dp))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Profile Image")

                Spacer(modifier = Modifier.width(20.dp))

                Text(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp,
                    text = "Pranish")
            }


            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "Edit Profile")
        }



    }
}

@Preview(showBackground = true
)
@Composable
fun ProfileGeneral(){
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
                text = "General")





            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Email, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "iampranish@Outlook.com")
                }
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Phone, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "04-123-456-78")
                }
            }
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.LocationOn, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "20 Albert parade, Ashfield 2131")
                }
            }
        }
    }
}

@Composable
fun ProfileNotification(){
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
                    .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Password")
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