package com.phoenix.ecommerce.data.local.cartDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.phoenix.ecommerce.data.data.product.Products

@Dao
interface CartDao {
    @Query("SELECT * FROM product")
    fun getALl() : LiveData<List<CartProduct>>

    @Insert
    fun insert(products: CartProduct)

    @Update
    fun update(products: CartProduct)

    @Delete
    fun delete(products: CartProduct)

    @Query("SELECT * FROM product WHERE product_id = :productId")
    fun getItemByID (productId : String) : CartProduct?

    @Query("Delete FROM product")
    fun clear()

}