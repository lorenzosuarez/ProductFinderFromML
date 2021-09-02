package com.example.productfinderfromml.data.model.detail


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("accepts_mercadopago")
    val acceptsMercadopago: Boolean,
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("automatic_relist")
    val automaticRelist: Boolean,
    @SerializedName("available_quantity")
    val availableQuantity: Int,
    @SerializedName("base_price")
    val basePrice: Int,
    @SerializedName("buying_mode")
    val buyingMode: String,
    @SerializedName("catalog_listing")
    val catalogListing: Boolean,
    @SerializedName("catalog_product_id")
    val catalogProductId: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("channels")
    val channels: List<String>,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("coverage_areas")
    val coverageAreas: List<Any>,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("deal_ids")
    val dealIds: List<Any>,
    @SerializedName("descriptions")
    val descriptions: List<Description>,
    @SerializedName("differential_pricing")
    val differentialPricing: Any,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("health")
    val health: Any,
    @SerializedName("id")
    val id: String,
    @SerializedName("initial_quantity")
    val initialQuantity: Int,
    @SerializedName("international_delivery_mode")
    val internationalDeliveryMode: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("listing_source")
    val listingSource: String,
    @SerializedName("listing_type_id")
    val listingTypeId: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("non_mercado_pago_payment_methods")
    val nonMercadoPagoPaymentMethods: List<Any>,
    @SerializedName("official_store_id")
    val officialStoreId: Any,
    @SerializedName("original_price")
    val originalPrice: Any,
    @SerializedName("parent_item_id")
    val parentItemId: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("pictures")
    val pictures: List<Picture>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("sale_terms")
    val saleTerms: List<SaleTerm>,
    @SerializedName("secure_thumbnail")
    val secureThumbnail: String,
    @SerializedName("seller_address")
    val sellerAddress: SellerAddress,
    @SerializedName("seller_contact")
    val sellerContact: Any,
    @SerializedName("seller_id")
    val sellerId: Int,
    @SerializedName("shipping")
    val shipping: Shipping,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("sold_quantity")
    val soldQuantity: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("stop_time")
    val stopTime: String,
    @SerializedName("sub_status")
    val subStatus: List<Any>,
    @SerializedName("subtitle")
    val subtitle: Any,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnail_id")
    val thumbnailId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("variations")
    val variations: List<Any>,
    @SerializedName("video_id")
    val videoId: Any,
    @SerializedName("warnings")
    val warnings: List<Any>,
    @SerializedName("warranty")
    val warranty: String
)