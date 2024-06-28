package com.phoenix.ecommerce.data.local.repository

import androidx.lifecycle.LiveData
import com.phoenix.ecommerce.data.local.cartDatabase.CartProduct
import com.phoenix.ecommerce.data.local.cartDatabase.CartDao

class CartRepository(private val cartDao: CartDao) {

    val getAllCartProduct: LiveData<List<CartProduct>> = cartDao.getALl()


    suspend fun addToCart(cartProduct: CartProduct){
        cartDao.insert(cartProduct )
    }

    suspend fun updateCart(cartProduct: CartProduct){
        cartDao.update(cartProduct)
    }



    suspend fun removeFromCart(cartProduct: CartProduct){
        cartDao.delete(cartProduct)
    }

    suspend fun getItemByID (productId : String) : CartProduct? {
        return cartDao.getItemByID(productId)
    }

}