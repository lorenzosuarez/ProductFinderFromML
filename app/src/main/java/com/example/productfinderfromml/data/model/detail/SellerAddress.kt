package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class SellerAddress(
    @SerializedName("city")
    val city: City,
    @SerializedName("country")
    val country: Country,
    @SerializedName("id")
    val id: Int,
    @SerializedName("search_location")
    val searchLocation: SearchLocation,
    @SerializedName("state")
    val state: StateX
)