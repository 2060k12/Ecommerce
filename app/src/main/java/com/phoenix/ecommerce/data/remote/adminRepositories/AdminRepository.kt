package com.phoenix.ecommerce.data.remote.adminRepositories

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.phoenix.ecommerce.data.data.product.Products
import java.util.UUID

class AdminRepository {

    // initialization of firebase auth and  firestore database
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    // fire storage
    private val storage = FirebaseStorage.getInstance()
    private val storageRef = storage.reference

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
                                .add(products)
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

}

