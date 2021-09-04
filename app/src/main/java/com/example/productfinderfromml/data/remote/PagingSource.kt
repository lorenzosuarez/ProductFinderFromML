package com.example.productfinderfromml.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productfinderfromml.BuildConfig
import com.example.productfinderfromml.data.model.item.Results
import com.example.productfinderfromml.domain.DefaultRepository.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 0

@ExperimentalPagingApi
class PagingSource(private val service: WebService, private val query: String, private val sort : String) : PagingSource<Int, Results>() {
    private val TAG = PagingSource::class.java.simpleName

    @ExperimentalCoroutinesApi
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val offset = if (params.key != null)  ((((position - 1) * NETWORK_PAGE_SIZE) ) - NETWORK_PAGE_SIZE ) else STARTING_PAGE_INDEX

        return try {
            val response = service.searchProduct(BuildConfig.ML_COUNTRY, query = query, offset = offset , limit = NETWORK_PAGE_SIZE, sort = sort )
            val nextKey = if (response.results.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            Log.e(TAG, exception.message ?: "IOException")
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            Log.e(TAG, exception.message ?: "HttpException")
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}