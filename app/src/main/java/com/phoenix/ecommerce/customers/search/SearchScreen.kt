package com.phoenix.ecommerce.customers.search

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.utils.BottomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController){
    val context = LocalContext.current
    val searchInputText = remember{ mutableStateOf("")}

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {

                        Icon(painter = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowBack) , contentDescription = "Back")
                    }
                },
                title = {
                Text(text = "Search")
            }
            
            )
            
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        },

        content = {
            innerPadding->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                
                item {
                    SearchScreenBar(
                        value = searchInputText.value,
                        onValueChange = {
                            searchInputText.value = it
                        }) {
                        Toast.makeText(context , searchInputText.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

    )

}



@Composable
fun SearchScreenPreview(){
    SearchScreen(rememberNavController())
}