package com.example.productfinderfromml.data.remote

import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Resultado
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */
@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getCocktailByName(query: String): Flow<Resource<List<Resultado>>> =
        callbackFlow {
            offer(
                Resource.Success(
                    webService.searchProduct(query).results
                )
            )
            awaitClose { close() }
        }
}