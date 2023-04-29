package com.app.supermarket.domain.usecase

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.AddressResponse
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.presentation.address.UserAddressUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CreateUpdateUserAddressUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(userAddressUiState: UserAddressUiState): Flow<Resource<BaseResponse<AddressResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(repository.addUserAddress(userAddressUiState.toAddressRequest()))
        }.flowOn(Dispatchers.IO)
}
