package com.phoenix.ecommerce.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.repository.ProductsRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {


    // initializing products repository
    private val productRepository = ProductsRepository()

    // livedata for our product list
    val produstList = productRepository.productList

    // function to get allProducts from firestore database
    // getAllProduct is a suspend function in ProductRepository
    fun getAllProducts(){
        viewModelScope.launch {
            productRepository.getAllProducts()
        }
    }

}