package com.phoenix.ecommerce.data.remote.repository

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.phoenix.ecommerce.R
import com.phoenix.ecommerce.data.data.product.Products
import kotlinx.coroutines.tasks.await

class ProductsRepository {

    // initializing firestore database
    val db = Firebase.firestore

    // mutable State for product
    private  val _clickedProduct = mutableStateOf<Products?>(null)
    val clickedProduct = _clickedProduct

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

    // get a product by it's id
    suspend fun getProduct(productId : String){

        try {
            val products = db.collection("products")
                .get()
                .await()
            for (product in products) {
                if (product.id.toString() == productId) {
                    val tempProduct = product.toObject<Products>()
                    _clickedProduct.value = tempProduct
                }
            }
        }
        catch (e: Exception){
            Log.e("Error Fetching Data", e.message.toString())
        }
    }



}