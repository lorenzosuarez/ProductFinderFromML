package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class Rule(
    @SerializedName("default")
    val default: Boolean,
    @SerializedName("free_mode")
    val freeMode: String,
    @SerializedName("free_shipping_flag")
    val freeShippingFlag: Boolean,
    @SerializedName("value")
    val value: Any
)