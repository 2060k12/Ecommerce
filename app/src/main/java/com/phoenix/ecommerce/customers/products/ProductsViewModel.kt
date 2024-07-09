package com.phoenix.ecommerce.customers.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.remote.repository.ProductsRepository
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {

    // Initializing products repository
    private val productsRepository = ProductsRepository()

    // all ordered products by the current user
    val orderedProductList get() = productsRepository.orderedProductList

    val clickedProduct get() = productsRepository.clickedProduct

    // stores all reviews of a product
    val allReviews = productsRepository.allReviews

    fun getProduct(productId : String, category: String ){
        viewModelScope.launch {
            productsRepository.getProduct(productId, category)
        }
    }

    // get all ordered list
    fun getALlOrderedList(){
        viewModelScope.launch {
            productsRepository.getALlOrderedProducts()
        }
    }


    // fun to add reviews
    fun addReview(products: Products, s: String, i: Int) {
            viewModelScope.launch {
                productsRepository.addReviews(products, i, s)
            }
    }


    // get all reviews of a product
    fun getAllReviews(products: Products){
        viewModelScope.launch {
            productsRepository.getALlReviews(products)
        }
    }


}