package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class ValueX(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("struct")
    val struct: StructX
)