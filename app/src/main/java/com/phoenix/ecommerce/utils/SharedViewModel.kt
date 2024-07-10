package com.phoenix.ecommerce.utils

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.data.profile.Profile
import kotlinx.serialization.Serializable

@Serializable
class SharedViewModel : ViewModel() {
    
    // edit detail for example, if you are editing phoneNumber it will be phone.
    var editDetail = ""
        private set

    // to use edit profile feature
    var profileInfo = Profile()
        private  set

    var product = Products()
        private set

    fun addProduct(newProduct: Products) {
        product = newProduct
    }

    var listOfProducts = ArrayList<Products>()
        private set


    fun addListOfProduct(newProduct: ArrayList<Products>){
        listOfProducts = newProduct
    }

    fun addProfileInfo(newProfile: Profile){
        profileInfo = newProfile
    }

    fun addEditDetails (detail : String){
        editDetail = detail
    }


}