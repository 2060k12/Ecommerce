package com.phoenix.ecommerce.products

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.phoenix.ecommerce.data.data.product.Products


@Composable
fun ProductImageView(products: Products){

    AsyncImage(
        model = products.productIconUrl,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(20.dp))
        ,
        contentDescription = ""
    )

}

@Composable
fun ProductDetail(products: Products){

    Column(modifier = Modifier.padding(16.dp, 8.dp, 16.dp,8.dp)) {
        Text(
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            text = products.productName)
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            text = "AU$ " + products.productCost.toString())
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            text = "5")
    }
}



@Composable
fun ProductsInfo(products: Products){
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = products.productInfo )
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






