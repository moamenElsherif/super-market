package com.app.supermarket.domain.usecase

import com.app.supermarket.base.Resource
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.presentation.main.settings.UserDataUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(userId: Int): Flow<Resource<UserDataUiState>> =
        flow {
            emit(Resource.Loading)
            when (val resource = repository.getUserData(userId)) {
                is Resource.Failure -> {
                    emit(resource)
                }
                is Resource.Success -> {
                    val data = resource.value.result
                    emit(Resource.Success(data.toUserDataUiState()))
                }
                else -> {}
            }

        }.flowOn(Dispatchers.IO)
}