package com.phoenix.ecommerce.customers.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct
import com.phoenix.ecommerce.data.local.cartDatabase.MyCartDatabase
import com.phoenix.ecommerce.data.local.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {


    var  cartList : LiveData<List<CartProduct>>
    private var repository: CartRepository

    init {
        val cartDao = MyCartDatabase.getDatabase(application).CartDao()
        repository = CartRepository(cartDao)
        cartList = repository.getAllCartProduct
    }

    fun addToCart(cartProduct: CartProduct){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(cartProduct)
        }
    }

    fun updateCart(cartProduct: CartProduct){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCart(cartProduct)
        }
    }

    fun removeFromCart(cartProduct: CartProduct){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(cartProduct)
        }
    }

    fun getItemByID (productId : String, callback : (CartProduct?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            callback(repository.getItemByID(productId))

        }
    }

    fun clear(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clear()
        }
    }

}