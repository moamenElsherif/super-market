package com.app.supermarket.presentation.address.mainaddress

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.base.address.UserAddress
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.data.models.response.AddressResponse
import com.app.supermarket.domain.usecase.CreateUpdateUserAddressUseCase
import com.app.supermarket.domain.usecase.GetUserAddressUseCase
import com.app.supermarket.presentation.address.UserAddressUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAddressViewModel @Inject constructor(
    private val updateUserAddressUseCase: CreateUpdateUserAddressUseCase,
    private val getUserAddressUseCase: GetUserAddressUseCase,
    private val auth: Auth
) : ViewModel() {

    private val addressPreference = UserAddress
    private val savedAddress = addressPreference.getUserAddress()

    var modifiedAddress = MutableLiveData<UserAddressUiState>()

    private var _addressResponse =
        MutableStateFlow<Resource<BaseResponse<AddressResponse?>>>(Resource.Default)
    val addressResponse: StateFlow<Resource<BaseResponse<AddressResponse?>>> = _addressResponse

    init {
        getUserAddress()
    }

    private fun getUserAddress() {
        viewModelScope.launch {
            if (savedAddress.apartmentNum.isEmpty()) {
                _addressResponse.value = Resource.Loading
                auth.getAuthData().userId?.let {
                    getUserAddressUseCase.invoke(it).collectLatest { resource ->
                        _addressResponse.value = resource
                    }
                }
            }
        }
    }

    fun updateAddress(){
        viewModelScope.launch {
            modifiedAddress.value?.let { updateUserAddressUseCase.invoke(it) }
        }
    }
}