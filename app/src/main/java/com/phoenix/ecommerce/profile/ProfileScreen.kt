package com.phoenix.ecommerce.profile

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.utils.BottomNavBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController){
    
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) {innerPadding->
        Surface(
            modifier = Modifier.padding(innerPadding)) {
            ProfileScreenTopBar(){
                Toast.makeText(context, "Profile Clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

@Composable
fun ProfileScreenPreview(){

    ProfileScreen(navController = rememberNavController())
}