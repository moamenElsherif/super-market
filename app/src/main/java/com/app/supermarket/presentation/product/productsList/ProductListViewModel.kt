package com.app.supermarket.presentation.product.productsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Constants
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.GetAllProductResponse
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.domain.usecase.GetAllCategoryProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getAllCategoryProductsUseCase: GetAllCategoryProductsUseCase
) : ViewModel() {

    private val _productsSearchResultsLiveData : MutableLiveData< List<ProductResponse>> = MutableLiveData()
    val productsSearchResultsLiveData : LiveData<List<ProductResponse>>
        get() = _productsSearchResultsLiveData

    private val _productsMutableList : MutableList<ProductResponse> = mutableListOf()

    private val _productsStateFlow = MutableStateFlow<Resource<BaseResponse<GetAllProductResponse>>>(Resource.Default)
    val productsStateFlow: StateFlow<Resource<BaseResponse<GetAllProductResponse>>>
        get() = _productsStateFlow

    fun getAllCategoryProductsById(categoryId: Int) {
        viewModelScope.launch {
            getAllCategoryProductsUseCase(categoryId).collectLatest { resource ->
                _productsStateFlow.value = resource
                if (resource is Resource.Success) {
                    _productsMutableList.addAll(resource.value.result.items ?: emptyList())
                }
            }
        }
    }

    /**
     * this function search in the [_productsMutableList] list which is a [List] of [ProductResponse]
     * for the  @param [value]
     * Then update [_productsSearchResultsLiveData] with the filtered new data
     * */
    fun searchProducts(value: String) {
        if (value.isEmpty()) {
            _productsSearchResultsLiveData.value = _productsMutableList
            return
        }

        // this is the lowercase string text of the search text to avoid text Char case difference problem
        val itemName = value.lowercase()
        val data = _productsMutableList
        val searchResult = data.filter { item ->
            val isFound = if (Locale.getDefault().language == Constants.arabic) item.localizedName?.lowercase()?.contains(itemName)
            else item.name?.lowercase()?.contains(itemName)
            isFound ?: false
        }
        _productsSearchResultsLiveData.value = searchResult
    }
}