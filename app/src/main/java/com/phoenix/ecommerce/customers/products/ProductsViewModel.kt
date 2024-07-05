package com.phoenix.ecommerce.customers.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.remote.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {

    // Initializing products repository
    private val productsRepository = ProductsRepository()

    val clickedProduct get() = productsRepository.clickedProduct

    fun getProduct(productId : String, category: String ){
        viewModelScope.launch {
            productsRepository.getProduct(productId, category)
        }
    }

}