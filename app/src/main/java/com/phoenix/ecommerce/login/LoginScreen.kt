package com.phoenix.ecommerce.login

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.navigation.Routes


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController){
    val email  = remember{ mutableStateOf("") }
    val password  = remember{ mutableStateOf("") }
    val context = LocalContext.current


    // viewmodel
    val viewModel : LoginViewModel = viewModel()

    Scaffold(
        bottomBar = {
            // Login button

            Column(
                modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp)
            ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 0.dp),
                shape = RoundedCornerShape(4.dp),
                onClick = {
                    viewModel.login(email.value, password.value){
                        success ->
                        if(success){
                            if(email.value == "admin@admin.com"){
                                navController.navigate(Routes.ADMIN_DASHBOARD){
                                    popUpTo(Routes.ADMIN_DASHBOARD){
                                        inclusive = true
                                    }
                                }
                            }
                            else
                            navController.navigate(Routes.HOME_SCREEN){
                                popUpTo(Routes.HOME_SCREEN){
                                    inclusive = true
                                }
                            }

                        }
                        else{
                            Toast.makeText(context, "Failed To login ", Toast.LENGTH_SHORT).show()
                        }
                    }

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
                onClick = {
                    navController.navigate(Routes.SIGNUP_SCREEN)
                }) {
                Text(text = "Sign up")

            }}
        },

        topBar = {
            TopAppBar(title = {
                Text(text = "Login ")
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
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
                    .padding(16.dp, 0.dp, 16.dp, 0.dp),
                label = {
                    Text(text = "Password")
                },


                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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


        }
    }}