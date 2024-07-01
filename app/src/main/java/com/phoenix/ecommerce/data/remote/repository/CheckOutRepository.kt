package com.phoenix.ecommerce.data.remote.repository

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.phoenix.ecommerce.customers.cart.CartViewModel
import com.phoenix.ecommerce.data.data.product.Products
import com.phoenix.ecommerce.data.local.cartDatabase.MyCartDatabase
import com.phoenix.ecommerce.data.local.repository.CartRepository

class CheckOutRepository {

    //firebase auth and firestore database
    private val db = Firebase.firestore
    private val auth = Firebase.auth


    fun checkOut(listOfCartProducts: List<Products>, address: String, callBack : (state : Boolean) -> Unit) {

        val timestamp = Timestamp.now()

        for (products in listOfCartProducts) {
            val deliveryInstruction = hashMapOf(
                "deliverTo" to auth.currentUser.toString(),
                "orderPlacedOn" to timestamp,
                "deliveryAddress" to address,
                "products" to products,
                "status" to "new"
            )
           db.collection("orders")
                .document()
                .set(deliveryInstruction)
               .addOnCompleteListener(){
                    callBack(true)
               }
               .addOnFailureListener(){
                   Log.e("Error", "error Checking out")
                   callBack(false)
               }


        }
    }
}