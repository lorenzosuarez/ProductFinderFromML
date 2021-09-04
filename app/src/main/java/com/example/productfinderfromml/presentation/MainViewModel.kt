package com.example.productfinderfromml.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.productfinderfromml.data.model.item.AvailableSort
import com.example.productfinderfromml.data.model.item.Results
import com.example.productfinderfromml.domain.Repository
import com.example.productfinderfromml.ui.adapters.ResultAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var currentQueryValue: String? = null
    private var _currentSearchResult: Flow<PagingData<Results>>? = null
    private val _spinner = MutableStateFlow(true)
    private val TAG = MainViewModel::class.java.simpleName

    private val _lastQuery = MutableLiveData<String>()

    private val _sortList = MutableLiveData<List<Pair<AvailableSort, Boolean>>>()
    val sortList: LiveData<List<Pair<AvailableSort, Boolean>>>
        get() = _sortList

    init {
        val listPair = listOf(
            AvailableSort(id = "relevance", name = "Más relevantes") to true,
            AvailableSort(id = "price_asc", name = "Menor precio") to false,
            AvailableSort(id = "price_desc", name = "Mayor precio") to false
        )
        _sortList.value = listPair
    }

    fun searchRepo(queryString: String? = _lastQuery.value, pSortName : String?): Flow<PagingData<Results>> {
        val sortId = pSortName ?:  "relevance"
        _lastQuery.value = queryString ?: ""

        val lastResult = _currentSearchResult
        if (queryString == currentQueryValue && lastResult != null && pSortName == null) {
            return lastResult
        }
        currentQueryValue = queryString
        Log.i(TAG, "Perform search: [$queryString)], Sort : [$sortId]")

        val newResult: Flow<PagingData<Results>> = repository.getSearchResultStream(_lastQuery.value!!, sortId)
            .cachedIn(viewModelScope)
        _currentSearchResult = newResult
        return newResult
    }
}