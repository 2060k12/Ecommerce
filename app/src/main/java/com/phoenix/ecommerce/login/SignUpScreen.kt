package com.phoenix.ecommerce.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.phoenix.ecommerce.navigation.Routes
import okhttp3.internal.threadName
import java.util.jar.Attributes.Name


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController){

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }

    // login viewmodel
    val viewModel : LoginViewModel = viewModel()

    // context
    val context = LocalContext.current



    Scaffold(

        topBar = {

            TopAppBar(title = {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "Register Now")
            })

        },

        bottomBar = {
            Column(
                modifier = Modifier.padding(0.dp,0.dp,0.dp,30.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = {
                    // Register as a new User
                        viewModel.signUpAsNewUser(email= email.value, password=password.value, name =name.value, phone=phoneNumber.value, address=address.value){
                            success->
                            if(success){
                                navController.navigate(Routes.LOGIN_SCREEN)
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                viewModel.signOut()
                            }
                            else{
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

                            }
                        }



                    }) {
                    Text(text = "Register")
                }
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Back to Login")

                }
            }
        }
    ) {
        innerPadding->
        Surface(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center

            ) {
                OutlinedTextField(
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Name")
                    },
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    })
                OutlinedTextField(
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Email")
                    },
                    value = email.value,
                    onValueChange = {
                        email.value = it
                    })
                OutlinedTextField(leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "")

                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Phone Number")
                    },
                    value = phoneNumber.value,
                    onValueChange = {
                        phoneNumber.value = it
                    })
                OutlinedTextField(
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Address")
                    },
                    value = address.value,
                    onValueChange = {
                        address.value = it
                    })
                OutlinedTextField(
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "")

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = {
                        Text(text = "Password")
                    },
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    })
            }

        }

    }

}
