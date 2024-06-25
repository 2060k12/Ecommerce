package com.phoenix.ecommerce.login

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.phoenix.ecommerce.R

@Composable
fun LoginTextBox (textBox: String, type: String ){
 val inputText = remember{ mutableStateOf("") }
    OutlinedTextField(
        placeholder = {
            Text(text = textBox)
        },
        value = "",
        onValueChange = {

        },


        )
}

@Preview(showBackground = true)
@Composable
fun LoginComponentsPreview(){
LoginTextBox(textBox = "Email", type = "Email")
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginComponentsPreviewUi(){
    LoginTextBox(textBox = "Email", type = "Email")

}