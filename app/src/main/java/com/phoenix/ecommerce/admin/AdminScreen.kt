package com.phoenix.ecommerce.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.utils.AdminNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavController){

Scaffold(

    // top bar
    topBar = {
        TopAppBar(title = {
            Text(text = "Admin Panel")
        })
    },

    // bottom bar
    bottomBar = {
        AdminNavigationBar(navController)
    }

) {
    Surface(modifier = Modifier.padding(it)) {

    }
    
}

}


@Preview(showSystemUi = true)
@Composable
fun AdminScreenPreview(){
    AdminScreen(rememberNavController())

}