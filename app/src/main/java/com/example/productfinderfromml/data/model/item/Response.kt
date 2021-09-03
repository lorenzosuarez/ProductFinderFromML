package com.example.productfinderfromml.data.model.item


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Response(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>,
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>,
    @SerializedName("country_default_time_zone")
    val countryDefaultTimeZone: String,
    @SerializedName("filters")
    val filters: List<Any>,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("query")
    val query: String,
    @SerializedName("results")
    val results: List<Results>,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sort")
    val sort: Sort
)

@Parcelize
data class Results(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("price") val price: Double,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("seller") val seller: Seller,
    @SerializedName("shipping") val shipping: Shipping,
    @SerializedName("permalink") val permalink: String
) : Parcelable
