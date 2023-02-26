package com.app.supermarket.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : ViewModel() {

    private val _categoriesLiveData : MutableLiveData<List<Item>> = MutableLiveData()
    val categoriesLiveData : LiveData<List<Item>>
        get() = _categoriesLiveData

    private val _categoryStateFlow = MutableStateFlow<Resource<BaseResponse<CategoryHomeResponse>>>(Resource.Default)
    val categoryStateFlow: StateFlow<Resource<BaseResponse<CategoryHomeResponse>>> = _categoryStateFlow

    init {
        getAllCategory()
    }

    private fun getAllCategory(){
        viewModelScope.launch {
            categoryUseCase.invoke().collectLatest { resource ->
                _categoryStateFlow.value = resource
                if (resource is Resource.Success) {
                    _categoriesLiveData.value = resource.value.result.items
                }
            }
        }
    }

    fun searchCategories(value: String) {
        val data = _categoriesLiveData.value ?: emptyList()
        val searchResult = data.filter { item ->
            item.title.contains(value)
        }
        _categoriesLiveData.value = searchResult
    }
}