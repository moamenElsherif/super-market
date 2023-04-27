package com.app.supermarket.presentation.main.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.supermarket.base.Resource
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    auth: Auth
) : ViewModel() {
    private val _userDataUiState: MutableLiveData<UserDataUiState> = MutableLiveData()
    val userDataUiState: LiveData<UserDataUiState>
        get() = _userDataUiState

    init {
        val userData = auth.getAuthData()
        userData.userId?.let { id ->
            viewModelScope.launch {
                getUserDataUseCase(id).collectLatest { resource ->
                    if (resource is Resource.Success) {
                        val data : UserDataUiState = resource.value
                        _userDataUiState.value = data
                    }
                }
            }
        }
    }
}