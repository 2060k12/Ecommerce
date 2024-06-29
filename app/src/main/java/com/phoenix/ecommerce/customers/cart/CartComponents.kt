package com.phoenix.ecommerce.customers.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct


@Composable
fun EachCartItem(cartProduct: CartProduct){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(20.dp))){

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
           Row {
               AsyncImage(
                   contentScale = ContentScale.Crop,
                   modifier = Modifier
                       .size(60.dp)
                       .clip(RoundedCornerShape(10.dp)),
                   model = cartProduct.productIconUrl ,
                   contentDescription = "Product Image" ,)

               Spacer(modifier = Modifier.width(10.dp))
               Column {
                   Text(
                       fontWeight = FontWeight.Bold,
                       text = cartProduct.productName )
                   Text(text = "AU$ " + cartProduct.productCost.toString() + " X ${cartProduct.productCount}" + " = AU$ ${cartProduct.productCount?.times(
                       cartProduct.productCost
                   )}")
               }
           }

            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Right Arrow" )

        }


    }
}