package com.phoenix.ecommerce.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.Coil
import coil.compose.AsyncImage
import com.phoenix.ecommerce.R
import com.phoenix.ecommerce.data.data.product.Products

@Composable
fun OfferBanner(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp),
        ) {
        Row {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
        }

    }
}

@Composable
fun EachProducts(products: Products){


    Box(modifier = Modifier
        .width(180.dp)
        .wrapContentHeight()
        ){
        Column(
        ) {

            Card(
                shape = RectangleShape,
                modifier = Modifier
                    .size(180.dp)
            ) {
                
                AsyncImage(model = products.productIconUrl, contentDescription = "")
                    }

            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontSize = 20.sp,
                    text = products.productName)

                Text(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = products.productCost)
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}