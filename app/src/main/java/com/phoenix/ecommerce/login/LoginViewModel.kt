package com.phoenix.ecommerce.login

import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.repository.LoginRepository

class LoginViewModel :ViewModel() {

    // initialization of LoginRepository
    private val repository = LoginRepository()
}