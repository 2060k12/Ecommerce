package com.phoenix.ecommerce.data.data.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class Products(
    val productCategory : String,
    val productName :String,
    var productId: String,
    val productCost : Int,
    var productIconUrl : String,
    val productInfo : String,
    val productColor : List<String>,
    val productSpecs : List<String>,
)

