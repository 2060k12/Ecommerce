package com.phoenix.ecommerce

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.phoenix.ecommerce.login.LoginScreen
import com.phoenix.ecommerce.navigation.NavigationGraph
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.ui.theme.EcommerceTheme

class MainActivity : ComponentActivity() {

    private lateinit var startDestination : String
    private val auth by lazy { Firebase.auth }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startDestination = if(auth.currentUser!= null){
            Routes.HOME_SCREEN
        } else{
            Routes.LOGIN_SCREEN
        }
        setContent {

            EcommerceTheme {
               NavigationGraph(navHostController = rememberNavController(), startDestination)

            }
        }
    }

}