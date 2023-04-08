package com.app.supermarket.domain.di

import com.app.supermarket.base.AuthPreference
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: Repository,
        saveAuthTokenUseCase: SaveAuthTokenUseCase
    ): LoginUseCase = LoginUseCase(repository, saveAuthTokenUseCase)

    @Provides
    @Singleton
    fun provideGetAllCategoryProductsUseCase(
        repository: Repository
    ): GetAllCategoryProductsUseCase = GetAllCategoryProductsUseCase(repository)

    @Provides
    @Singleton
    fun provideListAllUserCartItemsUseCase(
        repository: Repository
    ): ListAllUserCartItemsUseCase = ListAllUserCartItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveAuthTokenUseCase(
        authPreference: Auth
    ): SaveAuthTokenUseCase = SaveAuthTokenUseCase(authPreference)

    @Provides
    @Singleton
    fun provideGetUserDataUseCase(
        repository: Repository
    ): GetUserDataUseCase = GetUserDataUseCase(repository)
}