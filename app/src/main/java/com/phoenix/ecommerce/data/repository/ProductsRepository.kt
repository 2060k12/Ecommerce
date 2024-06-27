package com.phoenix.ecommerce.data.repository

import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.phoenix.ecommerce.data.data.product.Products
import kotlinx.coroutines.tasks.await

class ProductsRepository {

    // initializing firestore database
    val db = Firebase.firestore

    // livedata for product list
    private val _productList = MutableLiveData<ArrayList<Products>>()
    val productList : LiveData<ArrayList<Products>> = _productList

    // get all products from the database
    suspend fun getAllProducts(){
        val tempProductList = ArrayList<Products>()
        val products = db.collection("products")
            .get()
            .await()

        for(product in products ){
            val newProduct = product.toObject(Products::class.java)
            newProduct.productId = product.id
            tempProductList.add(newProduct)
        }
        _productList.value = tempProductList
    }



}