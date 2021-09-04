package com.example.productfinderfromml.data.remote


import com.example.productfinderfromml.data.model.item.Response
import com.example.productfinderfromml.data.model.detail.ProductDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

interface WebService {

    @GET("sites/{mlCountry}/search")
    suspend fun searchProduct(
        @Path("mlCountry") mlCountry: String,
        @Query("q") query: String,
        @Query("offset") offset: Int = 1,
        @Query("limit") limit: Int = 15,
        @Query("sort") sort: String = "",
    ): Response

    @GET("items")
    suspend fun productDetail(
        @Query("ids") ids: String
    ): List<ProductDetail>

}