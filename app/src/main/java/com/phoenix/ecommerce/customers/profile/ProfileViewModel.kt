package com.phoenix.ecommerce.customers.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.data.profile.Profile
import com.phoenix.ecommerce.data.remote.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel(){

    // initializing profile repository
    private val repository = ProfileRepository()

    // current Profile
    val currentUsersProfile  =  repository.currentUsersProfile


    // fun to get current userProfile

    fun getCurrentUserProfile(){
        repository.getProfileInfo()
    }

    // edit profile information
    fun editProfileNameImage(profile: Profile, editDetail : String, editedInfo: String, imageUrl: String ) {

        viewModelScope.launch(Dispatchers.IO) {
            if (imageUrl== profile.profileImage) {
                repository.editProfileInformation(profile, "name", editedInfo)
            } else {
                repository.editProfileInformation(profile, editDetail, editedInfo, imageUrl)

            }
        }
    }

    // edit profile information
    fun editProfileInformation(profile: Profile, editDetail : String, editedInfo: String) {
        viewModelScope.launch(Dispatchers.IO) {
                repository.editProfileInformation(profile, editDetail, editedInfo)
            }
        }

    fun resetPassword() {
        repository.resetPassword()
    }
}

