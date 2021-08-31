package com.example.productfinderfromml.data.model


import com.google.gson.annotations.SerializedName

data class Seller(
    @SerializedName("car_dealer")
    val carDealer: Boolean,
    @SerializedName("eshop")
    val eshop: Eshop?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("real_estate_agency")
    val realEstateAgency: Boolean,
    @SerializedName("registration_date")
    val registrationDate: String,
    @SerializedName("seller_reputation")
    val sellerReputation: SellerReputation,
    @SerializedName("tags")
    val tags: List<String>
)

data class Cancellations(
    @SerializedName("period")
    val period: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("value")
    val value: Int
)

data class Claims(
    @SerializedName("period")
    val period: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("value")
    val value: Int
)

data class DelayedHandlingTime(
    @SerializedName("period")
    val period: String,
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("value")
    val value: Int
)

data class Eshop(
    @SerializedName("eshop_experience")
    val eshopExperience: Int,
    @SerializedName("eshop_id")
    val eshopId: Int,
    @SerializedName("eshop_locations")
    val eshopLocations: List<Any>,
    @SerializedName("eshop_logo_url")
    val eshopLogoUrl: String,
    @SerializedName("eshop_rubro")
    val eshopRubro: Any,
    @SerializedName("eshop_status_id")
    val eshopStatusId: Int,
    @SerializedName("nick_name")
    val nickName: String? = "",
    @SerializedName("seller")
    val seller: Int,
    @SerializedName("site_id")
    val siteId: String
)

data class Metrics(
    @SerializedName("cancellations")
    val cancellations: Cancellations,
    @SerializedName("claims")
    val claims: Claims,
    @SerializedName("delayed_handling_time")
    val delayedHandlingTime: DelayedHandlingTime,
    @SerializedName("sales")
    val sales: Sales
)

data class Ratings(
    @SerializedName("negative")
    val negative: Double,
    @SerializedName("neutral")
    val neutral: Double,
    @SerializedName("positive")
    val positive: Double
)

data class Sales(
    @SerializedName("completed")
    val completed: Int,
    @SerializedName("period")
    val period: String
)

data class SellerReputation(
    @SerializedName("level_id")
    val levelId: String,
    @SerializedName("metrics")
    val metrics: Metrics,
    @SerializedName("power_seller_status")
    val powerSellerStatus: String,
    @SerializedName("transactions")
    val transactions: Transactions
)

data class Transactions(
    @SerializedName("canceled")
    val canceled: Int,
    @SerializedName("completed")
    val completed: Int,
    @SerializedName("period")
    val period: String,
    @SerializedName("ratings")
    val ratings: Ratings,
    @SerializedName("total")
    val total: Int
)
