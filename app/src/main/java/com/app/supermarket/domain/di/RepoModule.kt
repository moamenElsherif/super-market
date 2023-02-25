package com.app.supermarket.domain.di

import com.app.supermarket.data.datasource.DataSource
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.data.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideRepository(
        dataSource: DataSource
    ):Repository = RepositoryImpl(dataSource)
}