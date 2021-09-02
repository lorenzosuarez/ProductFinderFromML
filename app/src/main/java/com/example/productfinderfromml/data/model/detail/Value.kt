package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("struct")
    val struct: Struct
)