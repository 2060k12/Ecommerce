package com.phoenix.ecommerce.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReviewStars(color : Color, numberOfStars : Int) {
    Row {
        for(i in 1..5) {
            if(numberOfStars >= i){
                Icon(tint = color,
                    imageVector = Icons.Default.Star, contentDescription = "1")

            }
            else{
                Icon(tint = MaterialTheme.colorScheme.inverseOnSurface,
                    imageVector = Icons.Default.Star, contentDescription = "1")
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun ReviewStarPreview(){
    ReviewStars(Color(0xFFFFA534), 4)
}