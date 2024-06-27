package com.phoenix.ecommerce.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoenix.ecommerce.R


@Composable
fun ProfileScreenTopBar(onClick: () -> Unit){
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