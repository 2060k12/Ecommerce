package com.phoenix.ecommerce.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoenix.ecommerce.R


@Composable
fun ProductImageView(){

    Image(
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(10.dp))
        ,
        painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")

}

@Composable
fun ProductDetail(productName : String, productCost: String, productRating : String){

    Column(modifier = Modifier.padding(16.dp, 8.dp, 16.dp,8.dp)) {
        Text(
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            text = productName)
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            text = productCost )
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            text = productRating)
    }
}



@Composable
fun ProductAddQuantitySelector(){
    // TODO: Drop down  
}

@Composable
fun ProductSpecs(title: String, description: String){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(
            fontSize = 20.sp,
            text = title)

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            fontSize = 14.sp,
            text = description)
    }
}


@Composable
fun ProductsCustomerReview(){

}






