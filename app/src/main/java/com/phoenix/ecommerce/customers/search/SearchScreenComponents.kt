package com.phoenix.ecommerce.customers.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SearchScreenBar( value: String, onValueChange : (change: String)-> Unit, onClick : ()->Unit) {

    TextField(

        shape = RoundedCornerShape(5.dp),

        trailingIcon = {
            IconButton(onClick = {onClick()}) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")

            }
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = {
            Text(text = "Search")
        },
        value = value,
        onValueChange = {
            onValueChange(it)
        }

        )

}