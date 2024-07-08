package com.phoenix.ecommerce.data.data

import com.google.firebase.Timestamp
import com.phoenix.ecommerce.data.data.product.Products
import java.util.UUID

data class AdminReceivedOrder(
    val deliverTo: String,
    val deliveryAddress :String,
    val orderPlacedOn :Timestamp,
    val products : Products,
    var status: String,
    val contactNumber : String,
    val orderId : String ,
    val email : String,

    ) {
    constructor(): this("","",Timestamp.now(),Products(), "", "", "","" )
}
