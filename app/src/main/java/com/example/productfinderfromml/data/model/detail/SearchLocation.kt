package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class SearchLocation(
    @SerializedName("city")
    val city: CityX,
    @SerializedName("neighborhood")
    val neighborhood: Neighborhood,
    @SerializedName("state")
    val state: State
)