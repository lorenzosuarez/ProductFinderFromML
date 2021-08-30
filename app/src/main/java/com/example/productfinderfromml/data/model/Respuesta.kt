package com.example.productfinderfromml.data.model


import com.google.gson.annotations.SerializedName

data class Respuesta(
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
    val results: List<Resultado>,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sort")
    val sort: Sort
)

data class Resultado(
    @SerializedName("id") val id: String,
    @SerializedName("site_id") val siteId: String,
    @SerializedName("price") val price: Double,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnail") val thumbnail: String
)
