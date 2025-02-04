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

    // liveData for refunds
    private val _refundList = MutableLiveData<ArrayList<AdminReceivedOrder>>()
    val refundList : LiveData<ArrayList<AdminReceivedOrder>> get() = _refundList

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

    // fun to get list of all new orders
    suspend fun getAllOrders(){
        try{
            val tempListNew = ArrayList<AdminReceivedOrder>()
            val tempListCompleted = ArrayList<AdminReceivedOrder>()
            val tempProcessing = ArrayList<AdminReceivedOrder>()
            val tempRefunds = ArrayList<AdminReceivedOrder>()
        val orders =db.collection("orders")
            .get()
            .await()
            for(order in orders){
                val temp = order.toObject<AdminReceivedOrder>()
                when(temp.status.lowercase().trim()){
                    "new" -> tempListNew.add(temp)
                    "processing" -> tempProcessing.add(temp)
                    "done" -> tempListCompleted.add(temp)
                    "refund" -> tempRefunds.add(temp)
                }
            }
            _listOfReceivedOrders.value = tempListNew
            _processingOrdersList.value = tempProcessing
            _refundList.value = tempRefunds
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
                db.collection("users")
                    .document(products.email)
                    .collection("completedOrders")
                    .document(products.orderId)
                    .update("status", "Processing")
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
                    .document(products.orderId)
                    .update("status", "Done")
            }

    }


    // check if the current product is already on deals
    // if the item/ product in on deal callback have status as true
    // if not on del status is false
    suspend fun checkIfItemOnDeal(products: Products, callback: (status: Boolean) -> Unit){

        try {
            val document = db.collection("offers")
                .document(products.productId)
                .get()
                .await()

            if(document.data != null) {
                callback(true)
            }
            else {
                callback(false)
            }

        }
        catch (e: Exception){
            Log.e("error", e.message.toString())
        }
    }


    // fun to remove items from deals
    suspend fun removeItemsFromDeals(products: Products){
        try {
            val items = db.collection("offers")
                .document(products.productId)
                .delete()
                .await()
        }
        catch (e: Exception){
            Log.e("error", e.message.toString())
        }
    }


    // fun to add discount to apps
    suspend fun priceChange(products: Products, amount: Float){
        try {
            db.collection(products.productCategory)
                .document(products.productId)
                .update("discountedPrice", amount)
                .await()


        }
        catch (e: Exception){
            Log.e("Error", e.message.toString())
        }
    }

    fun removeProduct(products: Products, callback: (status: Boolean) -> Unit) {
        db.collection(products.productCategory)
            .document(products.productId)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }

    }

    // refund an item
     fun refund(item: AdminReceivedOrder, callback: (status: Boolean) -> Unit) {
        try {
            db.collection("orders")
                .document(item.orderId)
                .update("status", "refund")
                .addOnSuccessListener {
                    db.collection("users")
                        .document(item.email)
                        .collection("completedOrders")
                        .document(item.orderId)
                        .update("status", "refund")
                    callback(true)
                }

        }catch (e: Exception){
            Log.e("error", e.message.toString())
        }
    }

    suspend fun updateStockCount(products: Products, newStock: Int) {
        try {
            db.collection(products.productCategory)
                .document(products.productId)

                .update("currentlyOnStock", newStock)
                .await()

        }catch (e: Exception)
        {
            Log.e("error", e.message.toString())
        }

    }

    // Sign out admin
    fun signOut() {
        auth.signOut()
    }


}

