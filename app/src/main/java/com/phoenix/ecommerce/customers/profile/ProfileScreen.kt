package com.phoenix.ecommerce.customers.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.login.LoginViewModel
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    
    // loginViewModel
    val loginViewModel : LoginViewModel = viewModel()
    
    val context = LocalContext.current
    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(text = "Profile")
            })
            
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ProfileUserName(){
                    Toast.makeText(context, "Profile Clicked", Toast.LENGTH_SHORT).show()
                }

                ProfileGeneral()
                ProfileNotification()
                SignOutFeature(){
                    loginViewModel.signOut()
                    navController.navigate(Routes.LOGIN_SCREEN){
                        popUpTo(Routes.LOGIN_SCREEN){
                            inclusive = true
                        }
                    }


                }
                ProfileVersion()
            }


        }

    }
}

@Composable
fun ProfileScreenPreview(){

    ProfileScreen(navController = rememberNavController())
}