package com.example.productfinderfromml.data.model.item


import com.google.gson.annotations.SerializedName

data class AvailableSort(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)