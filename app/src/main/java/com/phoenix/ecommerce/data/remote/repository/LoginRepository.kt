package com.phoenix.ecommerce.data.remote.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginRepository {
    // firebase authentication
    private val auth = Firebase.auth

   fun loginUser(email: String, password: String){

   }


    fun signUpAsNewUser(email: String, password: String, name :String, age: String ){

    }
}