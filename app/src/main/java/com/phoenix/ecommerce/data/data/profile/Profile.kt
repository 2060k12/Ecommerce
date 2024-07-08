package com.phoenix.ecommerce.data.data.profile

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val profileImage: String,
    val name : String,
    val email :String,
    val phone :String,
    val address : String
){

    constructor(): this("","","","","")
}

