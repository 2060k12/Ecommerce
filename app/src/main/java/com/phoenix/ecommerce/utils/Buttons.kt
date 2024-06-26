package com.phoenix.ecommerce.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun OutlinedButton(buttonName: String, onButtonClick : () -> Unit){
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { onButtonClick() }) {
        Text(text = buttonName)
    }

}


@Composable
fun FilledButton(buttonName: String, onButtonClick : () -> Unit){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { onButtonClick() }) {
        Text(text =buttonName)
    }
}