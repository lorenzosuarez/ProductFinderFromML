package com.example.productfinderfromml.data.model.item


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("results")
    val results: Int
)