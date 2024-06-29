package com.phoenix.ecommerce.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.product.Products

class SharedViewModel : ViewModel() {

    var product by mutableStateOf<Products>(Products("","","",0,"","","",""))
        private set

    fun addProduct(newProduct : Products){
        product = newProduct
    }


}