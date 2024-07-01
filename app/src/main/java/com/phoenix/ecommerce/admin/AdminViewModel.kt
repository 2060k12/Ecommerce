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



    fun addNewProduct(products: Products){
        repository.addNewProduct(products)

        // TODO: Add firestorage for image
    }

    fun getAllReceivedOrders() {
        viewModelScope.launch {
            repository.getAllOrders()
        }
    }
}