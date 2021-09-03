package com.example.productfinderfromml.domain

import androidx.paging.PagingData
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.item.Results
import com.example.productfinderfromml.data.model.detail.ProductDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

interface Repository {
    fun getSearchResultStream(query: String, sort: String) : Flow<PagingData<Results>>
    suspend fun getProductDetail(ids: String) : Resource<List<ProductDetail>>
}