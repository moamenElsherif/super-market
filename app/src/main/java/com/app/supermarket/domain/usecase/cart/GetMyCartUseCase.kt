package com.app.supermarket.domain.usecase.cart

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.MyCartResponse
import com.app.supermarket.data.repo.cart.CartRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMyCartUseCase @Inject constructor(
    private val cartRepo: CartRepo
) {

    operator fun invoke(): Flow<Resource<BaseResponse<MyCartResponse>>> =
        flow {
            emit(Resource.Loading)
            emit(cartRepo.getMyCart())
        }.flowOn(Dispatchers.IO)
}