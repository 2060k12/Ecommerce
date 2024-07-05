package com.phoenix.ecommerce.customers.checkout

import androidx.lifecycle.ViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.remote.repository.CheckOutRepository

class CheckOutViewModel: ViewModel() {
    // initializing CheckOut Repository
    private val repository = CheckOutRepository()



    fun checkOut(listOfCartProducts: List<Products>, address: String,  callBack : (state : Boolean) -> Unit) {
        repository.checkOut(listOfCartProducts, address, callBack )
    }


}