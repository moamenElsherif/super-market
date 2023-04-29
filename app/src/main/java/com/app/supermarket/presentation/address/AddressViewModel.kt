package com.app.supermarket.presentation.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.Resource
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.domain.usecase.GetUserAddressUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val getUserAddressUseCase: GetUserAddressUseCase,
    auth: Auth
) : ViewModel() {
    private val _userAddress: MutableLiveData<UserAddressUiState> = MutableLiveData(
        UserAddressUiState()
    )
    val userAddress: LiveData<UserAddressUiState>
        get() = _userAddress


    init {
        val userData = auth.getAuthData()
        if (userData.userId != null) {
            getUserAddress(userData.userId!!)
        } else {
            Timber.d("User Id is Null =============>")
        }
    }


    private fun getUserAddress(userId: Int) {
        viewModelScope.launch {
            getUserAddressUseCase(userId).collectLatest { resource ->
                if (resource is Resource.Success) {
                   resource.value.result?.let { addressResponse ->
                       _userAddress.value = addressResponse.toUserAddressUiState()
                   }
                }
            }
        }
    }


}