package com.example.productfinderfromml.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.productfinderfromml.R
import com.example.productfinderfromml.core.Resource
import com.example.productfinderfromml.data.model.detail.Picture
import com.example.productfinderfromml.data.model.detail.ProductDetail
import com.example.productfinderfromml.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Lorenzo Suarez on 28 August 2021
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val TAG = DetailViewModel::class.java.simpleName

    private val _productDetail = MutableLiveData<Resource<List<ProductDetail>>>()
    val productDetail: LiveData<Resource<List<ProductDetail>>>
        get() = _productDetail

    private val _pictures = MutableLiveData<List<Picture>>()
    val pictures: LiveData<List<Picture>>
        get() = _pictures

    fun getProductDetail(ids: Array<String>) =
        viewModelScope.launch {
            _productDetail.value = Resource.Loading
            try {
                _productDetail.value = repository.getProductDetail(ids.joinToString(separator = ","))
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "Exception")
                _productDetail.value = Resource.Failure(e)
            }
        }

    fun updatePictures(pictures: List<Picture>) {
        _pictures.value = pictures
    }

    enum class Status {
        STATUS1,
        STATUS2,
    }

    private var _status = MutableLiveData(Status.STATUS1)
    val status: LiveData<Status>
        get() = _status

    fun setStatus(status: Status) {
        _status.value = status
    }
}