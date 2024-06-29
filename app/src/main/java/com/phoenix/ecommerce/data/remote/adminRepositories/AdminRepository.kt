package com.phoenix.ecommerce.data.remote.adminRepositories

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.phoenix.ecommerce.data.data.product.Products

class AdminRepository {

    // initialization of firebase auth and  firestore database
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    // add new product to database
    fun addNewProduct(products: Products){
        db.collection(products.productCategory)
            .add(products)
    }

}