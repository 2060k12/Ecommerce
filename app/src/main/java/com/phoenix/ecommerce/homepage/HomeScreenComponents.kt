package com.phoenix.ecommerce.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.play.core.integrity.z
import com.phoenix.ecommerce.R

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
@Preview(showSystemUi = true)
fun EachProducts(){
    Box(modifier = Modifier
        .width(150.dp)
        .wrapContentHeight()
        ){
        Column(
        ) {

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(150.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Apple")

                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = "AU $24")
            }

        }
    }
}