package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String  = "",
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String = "",
    @SerializedName("value_struct")
    val valueStruct: ValueStruct,
    @SerializedName("values")
    val values: List<Value>
)