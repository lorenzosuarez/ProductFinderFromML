package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class SaleTerm(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("value_id")
    val valueId: Any,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("value_struct")
    val valueStruct: ValueStructX,
    @SerializedName("values")
    val values: List<ValueX>
)