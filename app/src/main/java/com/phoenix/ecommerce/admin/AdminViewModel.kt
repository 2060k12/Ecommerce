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
    val refundList : LiveData<ArrayList<AdminReceivedOrder>> get() = repository.refundList



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

    fun checkIfItemOnDeal(products: Products, callback: (status: Boolean) -> Unit ){
        viewModelScope.launch() {
            repository.checkIfItemOnDeal(products, callback)
        }
    }

    fun removeItemsFromDeals(products: Products){
        viewModelScope.launch {
            repository.removeItemsFromDeals(products)
        }
    }

    fun priceChange(products: Products, amount: Float){
        viewModelScope.launch {
            repository.priceChange(products, amount)
        }
    }

    fun removeProduct(products: Products, callback: (status: Boolean) -> Unit) {
        viewModelScope.launch {
            repository.removeProduct(products, callback)
        }
    }

    fun refundProduct(item: AdminReceivedOrder, callback: (status: Boolean) -> Unit) {
        viewModelScope.launch {
            repository.refund(item, callback)
        }

    }

    fun updateStockCount(products: Products, newStock: Int) {
        viewModelScope.launch {
            repository.updateStockCount(products, newStock)
        }

    }

    fun signOut() {
        repository.signOut()    }


}