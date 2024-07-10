package com.phoenix.ecommerce.customers.cart

import android.view.MotionEvent
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct
import com.phoenix.ecommerce.navigation.Routes


@Composable
fun EachCartItem(navController: NavController, cartProduct: CartProduct){
    Card (
        onClick = {
            navController.navigate(Routes.PRODUCT_SCREEN + "/${cartProduct.productId}/${cartProduct.productCategory}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))){
        Box(modifier = Modifier
            .fillMaxSize()){

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        model = cartProduct.productIconUrl,
                        contentDescription = "Product Image",
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = cartProduct.productName
                        )
                        Text(text = "Specs: ${cartProduct.productSpec} ")
                        Text(text = "Color: ${cartProduct.productColor} ")

                    }
                }

                Text(
                    fontWeight = FontWeight.Bold,
                    text = "AU$ ${cartProduct.productCost} "
                )


            }
        }


    }
}
