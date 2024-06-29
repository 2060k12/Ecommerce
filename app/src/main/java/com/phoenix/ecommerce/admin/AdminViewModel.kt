package com.phoenix.ecommerce.admin

import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.remote.adminRepositories.AdminRepository

class AdminViewModel: ViewModel() {

    // initializing Admin repository
    private val repository = AdminRepository()

    fun addNewProduct(products: Products){
        repository.addNewProduct(products)
    }
}