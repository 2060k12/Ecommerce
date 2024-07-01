package com.phoenix.ecommerce.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.product.Products
import kotlinx.serialization.Serializable

@Serializable
class SharedViewModel : ViewModel() {

    var product by mutableStateOf<Products>(
        Products(
            "",
            "",
            "",
            0,
            "",
            "",
            listOf(String()),
            listOf(String())
        )
    )
        private set

    fun addProduct(newProduct: Products) {
        product = newProduct
    }

    var listOfProducts = ArrayList<Products>()
        private set


    fun addListOfProduct(newProduct: ArrayList<Products>){
        listOfProducts = newProduct
    }
}