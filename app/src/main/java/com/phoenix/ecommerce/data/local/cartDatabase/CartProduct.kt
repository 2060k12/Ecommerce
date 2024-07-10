package com.phoenix.ecommerce.data.local.cartDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class CartProduct(
    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "card_id") val cardId : Int = 0,
    @ColumnInfo(name = "product_id") val productId : String,
    @ColumnInfo(name ="product_name") val productName :String,
    @ColumnInfo(name = "product_cost") val productCost : Int,
    @ColumnInfo(name = "product_icon_url") val productIconUrl : String,
    @ColumnInfo(name = "product_info")val productInfo : String?,
    @ColumnInfo(name = "item_count") var productCount : Int?,
    @ColumnInfo(name = "item_color")val productColor : String,
    @ColumnInfo(name = "item_spec")val productSpec : String,
    @ColumnInfo(name = "item_category")val productCategory : String,
    @ColumnInfo(name = "item_stock")val stockCount : Int,
    )
