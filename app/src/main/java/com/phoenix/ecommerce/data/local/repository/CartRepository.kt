package com.phoenix.ecommerce.data.local.repository

import androidx.lifecycle.LiveData
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct
import com.phoenix.ecommerce.data.local.cartDatabase.CartDao

class CartRepository(private val cartDao: CartDao) {

    val getAllCartProduct: LiveData<List<CartProduct>> = cartDao.getALl()


    fun addToCart(cartProduct: CartProduct){
        cartDao.insert(cartProduct )
    }

    fun updateCart(cartProduct: CartProduct){
        cartDao.update(cartProduct)
    }



    fun removeFromCart(cartProduct: CartProduct){
        cartDao.delete(cartProduct)
    }

    fun getItemByID (productId : String) : CartProduct? {
        return cartDao.getItemByID(productId)
    }

    suspend fun clear(){
        cartDao.clear()
    }

}