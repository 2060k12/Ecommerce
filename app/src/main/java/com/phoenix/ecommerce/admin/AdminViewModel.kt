package com.phoenix.ecommerce.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.data.AdminReceivedOrder
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.remote.adminRepositories.AdminRepository
import kotlinx.coroutines.launch

class AdminViewModel: ViewModel() {

    // initializing Admin repository
    private val repository = AdminRepository()

    // livedata
    val listOfReceivedOrder : LiveData<ArrayList<AdminReceivedOrder>> get() = repository.listOfReceivedOrder
    val completedOrdersList : LiveData<ArrayList<AdminReceivedOrder>> get() = repository.completedOrdersList
    val processingOrdersList : LiveData<ArrayList<AdminReceivedOrder>> get() = repository.processingOrdersList



    fun addNewProduct(products: Products){
        repository.addNewProduct(products)

        // TODO: Add firestorage for image
    }

    fun getAllReceivedOrders() {
        viewModelScope.launch {
            repository.getAllOrders()
        }
    }

    fun addProductToSpotlight(product: Products){
        repository.addProductToSpotlight(product)
    }

    fun addProductAsCompleted(products : AdminReceivedOrder){
        repository.addProductAsCompleted(products)
    }
    fun addProductsAsProcessing(products : AdminReceivedOrder){
        repository.addProductsAsProcessing(products)
    }



}