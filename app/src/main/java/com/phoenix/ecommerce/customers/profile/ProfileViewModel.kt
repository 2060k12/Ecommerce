package com.phoenix.ecommerce.customers.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.profile.Profile
import com.phoenix.ecommerce.data.remote.repository.ProfileRepository

class ProfileViewModel: ViewModel(){

    // initializing profile repository
    private val repository = ProfileRepository()

    // current Profile
    val currentUsersProfile  =  repository.currentUsersProfile


    // fun to get current userProfile

    fun getCurrentUserProfile(){
        repository.getProfileInfo()
    }

}