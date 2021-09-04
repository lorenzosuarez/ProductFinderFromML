package com.example.productfinderfromml.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.item.Results
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.data.remote.PagingSource
import com.example.productfinderfromml.data.remote.WebService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 10 August 2021
 */

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class DefaultRepository @Inject constructor(
    private val webService: WebService
) : Repository {

    @ExperimentalPagingApi
    override fun getSearchResultStream(query: String, sort: String): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(webService, "%${query.replace(' ', '%')}%", sort) }
        ).flow
    }

    override suspend fun getProductDetail(ids: String): Resource<List<ProductDetail>> {
        return Resource.Success(webService.productDetail(ids))
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}