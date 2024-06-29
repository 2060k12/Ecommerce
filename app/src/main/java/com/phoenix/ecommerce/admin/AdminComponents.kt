package com.phoenix.ecommerce.admin

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// card to add new product
@Composable
fun EditCardComponent(){
    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(80.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .clickable {
                    // TODO: add navigation to edit product screen
                }
                .fillMaxSize()
                .padding(24.dp)){
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Edit Product")
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "Arrow Right")

        }
    }
}


@Composable
fun galleryLauncher( callback : (imageLink: Uri)-> Unit): (() -> Unit){
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
                uri ->
            uri?.let {
                callback(it)
            }
        })
    return { galleryLauncher.launch("image/*") }

}