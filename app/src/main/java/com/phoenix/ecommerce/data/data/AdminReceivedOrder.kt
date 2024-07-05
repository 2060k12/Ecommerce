package com.phoenix.ecommerce.data.data

import com.google.firebase.Timestamp
import com.phoenix.ecommerce.data.data.product.Products

data class AdminReceivedOrder(
    val deliverTo: String,
    val deliveryAddress :String,
    val orderPlacedOn :Timestamp,
    val products : Products,
    val status: String,
    val contactNumber : String
) {
    constructor(): this("","",Timestamp.now(),Products(), "", "")
}
