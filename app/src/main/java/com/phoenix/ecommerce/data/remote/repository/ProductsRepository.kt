package com.phoenix.ecommerce.data.remote.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
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

    // mutable State for product
    private val _clickedProduct = mutableStateOf<Products?>(null)
    val clickedProduct = _clickedProduct


    // live data
    private val _productList =
        MutableLiveData<ArrayList<Products>>()    // livedata for product list
    val productList: LiveData<ArrayList<Products>> = _productList

    private val _computerList =
        MutableLiveData<ArrayList<Products>>()    // livedata for computer list
    val computerList: LiveData<ArrayList<Products>> = _computerList

    private val _mobileList = MutableLiveData<ArrayList<Products>>()    // livedata for mobile list
    val mobileList: LiveData<ArrayList<Products>> = _mobileList

    private val _watchList = MutableLiveData<ArrayList<Products>>()     // livedata for watch list
    val watchList: LiveData<ArrayList<Products>> = _watchList

    private val _offerList = MutableLiveData<ArrayList<Products>>()     // livedata for watch list
    val offerList: LiveData<ArrayList<Products>> = _offerList


    // get all products from the database
    suspend fun getAllProducts() {
        val tempProductList = ArrayList<Products>()
        getAllMobiles()
        getAllWatches()
        getALlComputers()

        if (_productList.value == null) {
            _productList.value = ArrayList()
        }

        // Add the contents of each list to the product list
        _mobileList.value?.let {
            _productList.value?.addAll(it)
        }
        _watchList.value?.let {
            _productList.value?.addAll(it)
        }
        _computerList.value?.let {
            _productList.value?.addAll(it)
        }


    }


    // get a product by it's id
    suspend fun getProduct(productId: String, category: String) {

        try {
            val products = db.collection(category)
                .get()
                .await()
            for (product in products) {
                if (product.id.toString() == productId) {
                    val tempProduct = product.toObject<Products>()
                    _clickedProduct.value = tempProduct
                }
            }
        } catch (e: Exception) {
            Log.e("Error Fetching Data", e.message.toString())
        }
    }

    suspend fun getALlComputers() {
        val tempList = ArrayList<Products>()
        val products = db.collection("computer")
            .get()
            .await()

        for (product in products) {
            val newProduct = product.toObject(Products::class.java)
            newProduct.productId = product.id
            tempList.add(newProduct)
        }
        _computerList.value = tempList
    }


    suspend fun getAllMobiles() {
        val tempList = ArrayList<Products>()
        val products = db.collection("mobile")
            .get()
            .await()

        for (product in products) {
            val newProduct = product.toObject(Products::class.java)
            newProduct.productId = product.id
            tempList.add(newProduct)
        }
        _mobileList.value = tempList
    }


    suspend fun getAllWatches() {
        val tempList = ArrayList<Products>()
        val products = db.collection("watch")
            .get()
            .await()

        for (product in products) {
            val newProduct = product.toObject(Products::class.java)
            newProduct.productId = product.id
            tempList.add(newProduct)
        }
        _watchList.value = tempList
    }


    // get all offers
    suspend fun getAllOffers() {
        val tempList = ArrayList<Products>()
        val products = db.collection("offers")
            .get()
            .await()

        for (product in products) {
            val newProduct = product.toObject(Products::class.java)
            newProduct.productId = product.id
            tempList.add(newProduct)
        }
        _offerList.value = tempList
    }


}