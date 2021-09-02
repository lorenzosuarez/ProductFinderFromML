package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class ValueStructX(
    @SerializedName("number")
    val number: Double,
    @SerializedName("unit")
    val unit: String
)