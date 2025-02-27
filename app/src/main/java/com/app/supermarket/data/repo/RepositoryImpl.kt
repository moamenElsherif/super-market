package com.app.supermarket.data.repo

import com.app.supermarket.base.BaseResponse
import com.app.supermarket.base.Resource
import com.app.supermarket.data.datasource.DataSource
import com.app.supermarket.data.models.request.AddressRequest
import com.app.supermarket.data.models.request.LoginRequest
import com.app.supermarket.data.models.request.RegisterRequest
import com.app.supermarket.data.models.response.*
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : Repository {

    override suspend fun login(loginRequest: LoginRequest): Resource<BaseResponse<LoginResponse>> = dataSource.login(loginRequest)

    override suspend fun register(registerRequest: RegisterRequest): Resource<BaseResponse<RegisterResponse>> = dataSource.register(registerRequest)

    override suspend fun categoriesFlow(): Resource<BaseResponse<CategoryHomeResponse>> = dataSource.listHomeCategories()
    override suspend fun listAllProductsByCategory(categoryId: Int): Resource<BaseResponse<GetAllProductResponse>> {
        return dataSource.listAllProductsByCategory(categoryId)
    }

    override suspend fun getProduct(categoryId: Int): Resource<BaseResponse<ProductResponse>> {
        return dataSource.getProduct(categoryId)
    }

    override suspend fun getUserData(userId: Int): Resource<BaseResponse<UserDataResponse>> {
        return dataSource.getUserData(userId)
    }

    override suspend fun listAllUserCartProducts(): Resource<BaseResponse<AllCartItemsResponse>> {
        return dataSource.listAllUserCartProducts()
    }

    override suspend fun getUserAddress(userId: Int): Resource<BaseResponse<AddressResponse?>> {
        return dataSource.getUserAddress(userId)
    }

    override suspend fun addUserAddress(addressRequest: AddressRequest): Resource<BaseResponse<AddressResponse>> {
        return dataSource.addUserAddress(addressRequest)
    }
}