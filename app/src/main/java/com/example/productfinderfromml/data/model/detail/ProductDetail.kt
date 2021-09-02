package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class ProductDetail(
    @SerializedName("body")
    val body: Body,
    @SerializedName("code")
    val code: Int
)