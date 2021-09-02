package com.example.productfinderfromml.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productfinderfromml.application.ToastHelper
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.Resultado
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
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
    private val _productDetail = MutableLiveData<Resource<List<ProductDetail>>>()
    val productDetail: LiveData<Resource<List<ProductDetail>>>
        get() = _productDetail




    fun searchRepo(queryString: String): Flow<PagingData<Resultado>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Resultado>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
    private val ids = arrayListOf("asdasd", "asdsad")

    /*val getProductDetail =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(Resource.Loading)
            try {
                repository.getProductDetail(ids.joinToString(separator = ",")).collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }*/

        /*viewModelScope.launch {
            _productDetail.value = Resource.Loading
            try {
                _productDetail.value = repository.getProductDetail(ids.joinToString(separator = ","))
            } catch (e: Exception) {
                _productDetail.value = Resource.Failure(e)
            }
        }*/

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