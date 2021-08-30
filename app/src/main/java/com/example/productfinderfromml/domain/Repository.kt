package com.example.productfinderfromml.domain

import androidx.paging.PagingData
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Respuesta
import com.example.productfinderfromml.data.model.Resultado
import kotlinx.coroutines.flow.Flow

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

interface Repository {
    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Resultado>>>
    fun getSearchResultStream(query: String) : Flow<PagingData<Resultado>>
    /*suspend fun saveFavoriteCocktail(cocktail: Cocktail)
    suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean
    suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>>
    suspend fun saveCocktail(cocktail: CocktailEntity)
    fun getFavoritesCocktails(): LiveData<List<Cocktail>>
    suspend fun deleteFavoriteCocktail(cocktail: Cocktail)*/
}