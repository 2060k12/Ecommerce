package com.phoenix.ecommerce.login

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.remote.repository.LoginRepository

class LoginViewModel :ViewModel() {

    // initialization of LoginRepository
    private val repository = LoginRepository()


    // SignUp as a new user
    fun signUpAsNewUser(email: String, password: String, name :String, phone: String, address: String, callback: (Boolean) -> Unit ){
        repository.signUpAsNewUser(email= email, password=password, name =name, phone=phone, address=address, callback)
    }

    fun signOut() {
        repository.signOut()
    }

    fun login(email: String, password: String, callback : (Boolean)-> Unit) {
        repository.loginUser(email, password, callback)

    }
}