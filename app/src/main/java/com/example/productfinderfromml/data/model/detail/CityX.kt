package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class CityX(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)