package com.phoenix.ecommerce.data.data.product
import kotlinx.serialization.Serializable

@Serializable
data class Products(
    val productCategory : String,
    val productName :String,
    var productId: String,
    val productCost : Int,
    var productIconUrl : String,
    val productInfo : String,
    val productColor : List<String>,
    val productSpecs : List<String>
){
    constructor():this("","","",0, "", "", listOf(String()), listOf(String()))
}

