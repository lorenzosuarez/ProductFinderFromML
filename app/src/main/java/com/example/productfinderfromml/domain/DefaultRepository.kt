package com.example.productfinderfromml.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.data.remote.PagingSource
import com.example.productfinderfromml.data.remote.NetworkDataSource
import com.example.productfinderfromml.data.remote.WebService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 10 August 2021
 */

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class DefaultRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val webService: WebService
    //private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Resultado>>> =
        callbackFlow {

            //offer(getCachedCocktails(cocktailName))

            networkDataSource.getCocktailByName(cocktailName).collect {
                when (it) {
                    is Resource.Success -> {
                        /*for (cocktail in it.data) {
                            saveCocktail(cocktail.asCocktailEntity())
                        }*/
                        //offer(getCachedCocktails(cocktailName))
                        offer(it)
                    }
                    is Resource.Failure -> {
                        //offer(getCachedCocktails(cocktailName))
                    }
                }
            }
            awaitClose { cancel() }
        }


    @ExperimentalPagingApi
    override fun getSearchResultStream(query: String): Flow<PagingData<Resultado>> {

        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(webService, "%${query.replace(' ', '%')}%") }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 3
    }

    /*override suspend fun saveFavoriteCocktail(cocktail: Cocktail) {
        //localDataSource.saveFavoriteCocktail(cocktail)
    }

    override suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean =
        //localDataSource.isCocktailFavorite(cocktail)

    override suspend fun saveCocktail(cocktail: CocktailEntity) {
        //localDataSource.saveCocktail(cocktail)
    }

    override fun getFavoritesCocktails(): LiveData<List<Cocktail>> {
        return LiveData<emptyList<Cocktail>()>// liveData<li>() //localDataSource.getFavoritesCocktails()
    }

    override suspend fun deleteFavoriteCocktail(cocktail: Cocktail) {
        localDataSource.deleteCocktail(cocktail)
    }

    override suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>> {
        return localDataSource.getCachedCocktails(cocktailName)
    }*/
}