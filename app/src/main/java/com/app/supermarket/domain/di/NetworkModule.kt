package com.app.supermarket.domain.di

import com.app.supermarket.data.api.ApiService
import com.app.supermarket.data.api.category.CategoryApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideCategoriesApiService(retrofit: Retrofit): CategoryApiService = retrofit.create(CategoryApiService::class.java)
}