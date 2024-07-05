package com.phoenix.ecommerce.customers.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// states for check box
var newAddressChecked   by mutableStateOf(false)
var checkedState by  mutableStateOf(false)

@Composable
fun CheckOutAddress (callback : (state : Boolean)-> Unit){

    Card(

        onClick = {
            newAddressChecked = !newAddressChecked
            callback(newAddressChecked)
            checkedState = false
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Checkbox(checked = newAddressChecked, onCheckedChange = {
                newAddressChecked = it
                checkedState = false
                callback(newAddressChecked)

            })
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                text = "Add new address"
            )
        }
    }
}
@Composable
fun CheckOutAddress(title: String, address : String, callback: (state : Boolean)-> Unit){



    Card(
        onClick = {
            checkedState = !checkedState
            callback(checkedState)
            newAddressChecked = false

        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()) {
        Row (verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Checkbox(
                checked = checkedState, onCheckedChange = {
                    checkedState = it
                    newAddressChecked = false
                    callback(it)
                } )
        Column(
        ) {
            Text(modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = title)
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = address
            )
        }



    }}

}

@Composable
fun CheckOutPayment(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun Preview(){

    

}