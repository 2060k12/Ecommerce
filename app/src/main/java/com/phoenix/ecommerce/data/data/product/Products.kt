package com.phoenix.ecommerce.data.data.product

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val productName :String,
    var productId: String,
    val productCost : Int,
    val productIconUrl : String,
    val productInfo : String,
) : Parcelable {
    constructor() : this( "",
        "",
        0,
        "",
        ""
        )
}
