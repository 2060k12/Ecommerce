package com.phoenix.ecommerce.data.remote.repository

import android.util.Log
import android.util.Log.v
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import com.google.android.play.integrity.internal.c
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.phoenix.ecommerce.data.data.profile.Profile
import kotlinx.coroutines.tasks.await

class ProfileRepository {

    // firebase authentication && firestore database
    private val auth = Firebase.auth
    private val db = Firebase.firestore

    // mutable data
    val currentUsersProfile : MutableState<Profile> =  mutableStateOf(Profile())

    // get current Profile Information
    fun getProfileInfo(){

        try {
            db
                .collection("users")
                .document(auth.currentUser?.email.toString())
                .get()
                .addOnSuccessListener {
                    val temp = it.toObject(Profile::class.java)
                    if (temp != null) {
                        currentUsersProfile.value = temp
                    }
                }

        }
        catch (e : Exception){
          Log.e("Error", e.message.toString())
        }
    }

    suspend fun editProfileInformation(profile : Profile, editDetail: String, editedInfo: String) {

        try {
            db.collection("users")
                .document(profile.email)
                .update(editDetail, editedInfo)
        }catch (e: Exception){
            Log.e("error", e.message.toString())
        }
    }

    suspend fun editProfileInformation(profile : Profile, editDetail: String, editedInfo: String, imageUrl : String) {
        try {
            val editInfo = mapOf(
                "name" to editedInfo,
                "profileImage" to imageUrl
            )
            db.collection("users")
                .document(profile.email)
                .update(editInfo)
        }catch (e: Exception){
            Log.e("error", e.message.toString())
        }
    }

    // send reset email
    fun resetPassword() {
        auth.sendPasswordResetEmail(auth.currentUser?.email.toString())
    }


}