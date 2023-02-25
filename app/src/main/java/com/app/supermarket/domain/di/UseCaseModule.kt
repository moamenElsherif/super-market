package com.app.supermarket.domain.di

import com.app.supermarket.data.repo.Repository
import com.app.supermarket.domain.usecase.LoginUseCase
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
        repository: Repository
    ): LoginUseCase = LoginUseCase(repository)
}