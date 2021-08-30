package com.example.productfinderfromml.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productfinderfromml.application.ToastHelper
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val toastHelper: ToastHelper,
    private val savedStateHandle: SavedStateHandle

) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Resultado>>? = null

    /*val fetchCocktailList = currentCocktailName.distinctUntilChanged().switchMap { cocktailName ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                repository.getCocktailByName(cocktailName).collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }*/


    fun searchRepo(queryString: String): Flow<PagingData<Resultado>> {
        /*val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }*/
        //currentQueryValue = queryString
        val newResult: Flow<PagingData<Resultado>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

/*
    fun saveOrDeleteFavoriteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            if (repository.isCocktailFavorite(cocktail)) {
                repository.deleteFavoriteCocktail(cocktail)
                //toastHelper.sendToast("Cocktail deleted from favorites")
            } else {
                repository.saveFavoriteCocktail(cocktail)
                //toastHelper.sendToast("Cocktail saved to favorites")
            }
        }
    }

    fun getFavoriteCocktails() =
        liveData<Resource<List<Cocktail>>>(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                emitSource(repository.getFavoritesCocktails().map { Resource.Success(it) })
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }

    fun deleteFavoriteCocktail(cocktail: Cocktail) {
        viewModelScope.launch {
            repository.deleteFavoriteCocktail(cocktail)
            //toastHelper.sendToast("Cocktail deleted from favorites")
        }
    }

    suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean =
        repository.isCocktailFavorite(cocktail)*/
}