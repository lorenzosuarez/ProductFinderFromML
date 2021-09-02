package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("dimensions")
    val dimensions: Any,
    @SerializedName("free_methods")
    val freeMethods: List<FreeMethod>,
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("local_pick_up")
    val localPickUp: Boolean,
    @SerializedName("logistic_type")
    val logisticType: String,
    @SerializedName("mode")
    val mode: String,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,
    @SerializedName("tags")
    val tags: List<String>
)