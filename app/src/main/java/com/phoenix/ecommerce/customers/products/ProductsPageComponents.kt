package com.phoenix.ecommerce.customers.products

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.play.integrity.internal.i
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.ReviewStars
import com.phoenix.ecommerce.utils.SharedViewModel


@Composable
fun ProductImageView(products: Products, navController: NavController){

    AsyncImage(
        model = products.productIconUrl,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(20.dp)),
        contentDescription = ""
    )

}
@Composable
fun ProductImageViewHomePage(products: Products, navController: NavController){

    AsyncImage(
        model = products.productIconUrl,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate(Routes.PRODUCT_SCREEN + "/${products.productId}/${products.productCategory}")
            },
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
        
        Spacer(modifier = Modifier.height(8.dp))
        ReviewStars(color = Color(0xffffa534), numberOfStars = 4)
        
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
fun ProductsCustomerReview(navController: NavController){

    Card(
        onClick = {
            navController.navigate(Routes.PRODUCT_REVIEW_SCREEN)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                text = " View Reviews"
            )

            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = "")
        }
    }

}


@Composable
fun ProductsChoseOptions(product: List<String>, callback: (selectedItem : String)-> Unit){

    Column (modifier = Modifier
        .padding(horizontal = 16.dp)){
        Text(
            fontWeight = FontWeight.Bold,
            text = "Color Options: ")

        val selected = remember {
            mutableStateOf("")
        }

        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){

            if (product != null) {
                for (product in product){
                    if(selected.value.lowercase() == product.lowercase()){
                        FilterChip(selected = true, onClick = {
                            selected.value = ""
                        },
                            label = { Text(text = product)})
                    }
                    else{
                        FilterChip(selected = false,
                            onClick = { selected.value = product },
                            label = { Text(text = product)})
                    }

                }
            }

            callback(selected.value)

        }
    }
}





