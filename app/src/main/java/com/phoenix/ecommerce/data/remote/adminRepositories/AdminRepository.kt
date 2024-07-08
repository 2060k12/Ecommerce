package com.phoenix.ecommerce.data.remote.adminRepositories

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import com.phoenix.ecommerce.data.data.AdminReceivedOrder
import com.phoenix.ecommerce.data.data.product.Products
import kotlinx.coroutines.tasks.await
import java.util.UUID

class AdminRepository {

    // initialization of firebase auth and  firestore database
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    // fire storage
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

    // liveData for new orders
    private val _listOfReceivedOrders = MutableLiveData<ArrayList<AdminReceivedOrder>>()
    val listOfReceivedOrder : LiveData<ArrayList<AdminReceivedOrder>> get() = _listOfReceivedOrders

    // liveData for completed orders
    private val _completedOrdersList = MutableLiveData<ArrayList<AdminReceivedOrder>>()
    val completedOrdersList : LiveData<ArrayList<AdminReceivedOrder>> get() = _completedOrdersList

    // liveData for processing
    private val _processingOrdersList = MutableLiveData<ArrayList<AdminReceivedOrder>>()
    val processingOrdersList : LiveData<ArrayList<AdminReceivedOrder>> get() = _processingOrdersList

    // add new product to database
    fun addNewProduct(products: Products){

        var downloadUrl : Uri
        val path: String = "${products.productCategory}/${products.productId}/post" + UUID.randomUUID() + ".png"
        val storageReference = storage.getReference(path)

        try {

        val uploadTask = storageReference.putFile(products.productIconUrl.toUri())
            .addOnSuccessListener {
                Log.i("Success", "Done")
            }
        val uriTask = uploadTask.continueWithTask{
                storageReference.downloadUrl
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            downloadUrl = task.result
                            products.productIconUrl = downloadUrl.toString()
                            db.collection(products.productCategory)
                                .document(products.productId)
                                .set(products)
                            Log.i("Url", downloadUrl.toString())
                        } else {
                            Log.i("Failed", "Failed to get download link")
                        }
                    }
            }

        }catch (e : Exception){
            Log.e("Error", "Uploading Failed")
        }


    }

    // TODO: Fix processing  
    // fun to get list of all new orders
    suspend fun getAllOrders(){
        try{
            val tempListNew = ArrayList<AdminReceivedOrder>()
            val tempListCompleted = ArrayList<AdminReceivedOrder>()
            val tempProcessing = ArrayList<AdminReceivedOrder>()
        val orders =db.collection("orders")
            .get()
            .await()
            for(order in orders){
                val temp = order.toObject<AdminReceivedOrder>()
                when(temp.status.lowercase().trim()){
                    "new" -> tempListNew.add(temp)
                    "processing" -> tempProcessing.add(temp)
                    "done" -> tempListCompleted.add(temp)
                }
            }
            _listOfReceivedOrders.value = tempListNew
            _processingOrdersList.value = tempProcessing
            _completedOrdersList.value = tempListCompleted
        }
        catch (e : Exception){
            Log.e("Error", "Error getting all orders + ${e.message.toString()}")
        }

    }


    // add a product to spotlight
    fun addProductToSpotlight(product: Products){
        db.collection("offers")
            .document(product.productId)
            .set(product)
    }


    // function to set new orders status as processing
    fun addProductsAsProcessing(products: AdminReceivedOrder){

        db.collection("orders")
            .document(products.orderId)
            .update("status", "Processing" )
            .addOnSuccessListener {
            }
    }

    // function to set status of processing orders to completed
    fun addProductAsCompleted(products: AdminReceivedOrder){
        products.status = "Done"
        db.collection("orders")
            .document(products.orderId)
            .update("status", "Done" )
            .addOnSuccessListener {
                db.collection("users")
                    .document(products.email)
                    .collection("completedOrders")
                    .add(products)
            }

    }



}

