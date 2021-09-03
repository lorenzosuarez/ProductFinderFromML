package com.example.productfinderfromml.data.model.item


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shipping(
    @SerializedName("free_shipping")
    val freeShipping: Boolean,
    @SerializedName("logistic_type")
    val logisticType: String,
    @SerializedName("mode")
    val mode: String,
    @SerializedName("store_pick_up")
    val storePickUp: Boolean,
    @SerializedName("tags")
    val tags: List<String>
) : Parcelable