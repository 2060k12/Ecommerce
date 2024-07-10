package com.phoenix.ecommerce.customers.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenix.ecommerce.login.LoginViewModel
import com.phoenix.ecommerce.navigation.Routes
import com.phoenix.ecommerce.utils.BottomNavBar
import com.phoenix.ecommerce.utils.SharedViewModel
import kotlinx.coroutines.launch
import kotlin.time.Duration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, sharedViewModel: SharedViewModel){

    // login view model
    val loginViewModel : LoginViewModel = viewModel()
    // Profile view model
    val viewModel : ProfileViewModel = viewModel()

    val profile by lazy {   viewModel.currentUsersProfile}
    viewModel.getCurrentUserProfile()

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val snackBarScope = rememberCoroutineScope()
    
    val context = LocalContext.current
    Scaffold(

        topBar = {
            TopAppBar(title = {
                Text(text = "Profile")
            })
            
        },
        bottomBar = {
            BottomNavBar(navController = navController, "profile")
        },
        snackbarHost = {
            SnackbarHost(hostState =snackBarHostState)
        }

    ) {innerPadding->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ProfileUserName(profile.value) {
                    sharedViewModel.addEditDetails("nameAndImage")
                    sharedViewModel.addProfileInfo(profile.value)
                    navController.navigate(Routes.EDIT_PROFILE_SCREEN )
                }

                ProfileGeneral(profile.value, navController, sharedViewModel)
                ProfileNotification(){
                    snackBarScope.launch {

                        snackBarHostState.showSnackbar("Reset Email sent")
                    }
                }
                SignOutFeature {
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
