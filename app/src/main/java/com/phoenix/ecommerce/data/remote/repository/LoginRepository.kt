package com.phoenix.ecommerce.data.remote.repository

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.phoenix.ecommerce.R
import kotlinx.coroutines.tasks.await
import okhttp3.Address
import okhttp3.Callback

class LoginRepository {
    // firebase authentication && firestore database
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    fun loginUser(email: String, password: String, callback : (Boolean)-> Unit) {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    task->
                    if(task.isSuccessful){
                        callback(true)
                    }
                    else{
                        callback(false)
                    }
                }
        }
        catch (e : Exception){
            callback(false)
            Log.e("Error Logging", e.message.toString())
        }
   }


    fun signUpAsNewUser(email: String, password: String, name :String, phone: String, address: String, callback: (Boolean) -> Unit ){
        val userInfo = hashMapOf(
            "name" to name,
            "phone" to phone,
            "email" to email,
            "address" to address,
            "profileImage" to "https://cdn.pixabay.com/photo/2016/03/31/19/56/avatar-1295397_1280.png"
        )
        try {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task->
                if(task.isSuccessful){
                    db.collection("users")
                        .document(email)
                        .set(userInfo)
                        .addOnCompleteListener{
                            callback(true)
                        }
                }
                else{

                    callback(false)
                }
            }}
        catch (e : Exception){
            callback(false)
            Log.e("Login Error", e.message.toString())
        }
    }

    // SignOut current User
    fun signOut() {
        try {
            auth.signOut()
        }
        catch (e :Exception){
            Log.e("Error Signing Out", e.message.toString())
        }
    }


}