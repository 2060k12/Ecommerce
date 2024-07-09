package com.phoenix.ecommerce.data.data.product

import com.google.firebase.Timestamp

data class Review(
    val userEmail :String,
    val comment : String,
    val rating : Int,
    val timestamp: Timestamp
){
    constructor(): this("","",0,Timestamp.now())
}
