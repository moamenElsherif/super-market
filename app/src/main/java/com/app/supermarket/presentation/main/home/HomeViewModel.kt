package com.app.supermarket.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Constants.arabic
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryHomeResponse
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.domain.usecase.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : ViewModel() {
    private val _categoriesSearchResultsLiveData : MutableLiveData<List<Item>> = MutableLiveData()
    val categoriesSearchResultsLiveData : LiveData<List<Item>>
        get() = _categoriesSearchResultsLiveData

    private val _categoriesLiveData : MutableLiveData<List<Item>> = MutableLiveData()
    private val categoriesLiveData : LiveData<List<Item>>
        get() = _categoriesLiveData

    private val _categoryStateFlow = MutableStateFlow<Resource<BaseResponse<CategoryHomeResponse>>>(Resource.Default)
    val categoryStateFlow: StateFlow<Resource<BaseResponse<CategoryHomeResponse>>> = _categoryStateFlow

    init {
        getAllCategory()
    }

    /**
     * this function list all the categories
     * into the [_categoriesLiveData] list which is a [List] of [Item]
     * */
    fun getAllCategory(){
        viewModelScope.launch {
            categoryUseCase().collectLatest { resource ->
                _categoryStateFlow.value = resource
                if (resource is Resource.Success) {
                    _categoriesLiveData.value = resource.value.result.items
                }
            }
        }
    }

    /**
     * this function search in the [categoriesLiveData] list which is a [List] of [Item]
     * for the  @param [value]
     * Then update [_categoriesSearchResultsLiveData] with the filtered new data
     * */
    fun searchCategories(value: String) {
        // this is the lowercase string text of the search text to avoid text Char case difference problem
        val itemName = value.lowercase()
        val data = categoriesLiveData.value ?: emptyList()
        val searchResult = data.filter { item ->
            val isFound = if (Locale.getDefault().language == arabic) item.localizedTitle.lowercase().contains(itemName)
            else item.title.lowercase().contains(itemName)
            isFound
        }
        _categoriesSearchResultsLiveData.value = searchResult
    }
}