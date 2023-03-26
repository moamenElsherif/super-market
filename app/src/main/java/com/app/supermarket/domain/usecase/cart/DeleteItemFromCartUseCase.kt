package com.app.supermarket.domain.usecase.cart

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.cart.AddToCartRequest
import com.app.supermarket.data.repo.cart.CartRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteItemFromCartUseCase @Inject constructor(
    private val cartRepo: CartRepo
) {

    operator fun invoke(productId: Int): Flow<Resource<BaseResponse<*>>> =
        flow {
            emit(Resource.Loading)
            emit(cartRepo.deleteFromCart(productId))
        }.flowOn(Dispatchers.IO)
}