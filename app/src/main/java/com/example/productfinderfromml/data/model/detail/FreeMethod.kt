package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class FreeMethod(
    @SerializedName("id")
    val id: Int,
    @SerializedName("rule")
    val rule: Rule
)