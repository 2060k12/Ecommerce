package com.phoenix.ecommerce.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.navigation.Routes


@Composable
fun LoginScreen(navController: NavController){
    val email  = remember{ mutableStateOf("") }
    val password  = remember{ mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center
        ) {

        // Email Text Field
        OutlinedTextField(
            value = email.value,
            onValueChange = {
            email.value = it
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            label = {
                Text(
                    text = "Email"
                )
            },
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Email),
            maxLines = 1
            )


        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                ,
            label = {
                Text(text = "Password")
            },



            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,

        )

        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(

                text = "forgot password?",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable {
                        Toast
                            .makeText(context, "forgot password?", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .padding(0.dp, 8.dp, 16.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(0.dp))
        // Login button

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 0.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = {

                navController.navigate(Routes.HOME_SCREEN)

            },
            ) {
            Text(text = "Log in")
            
        }

        // A pressable text: forgot password



        // signup button
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = { /*TODO*/ }) {
            Text(text = "Sign up")
            
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(navController = rememberNavController())
}