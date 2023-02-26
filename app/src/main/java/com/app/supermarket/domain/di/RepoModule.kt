package com.app.supermarket.domain.di

import com.app.supermarket.data.datasource.DataSource
import com.app.supermarket.data.datasource.category.CategoryRemoteDataSource
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.data.repo.RepositoryImpl
import com.app.supermarket.data.repo.category.CategoryRepository
import com.app.supermarket.data.repo.category.CategoryRepositoryImpl
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
    ) : Repository = RepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideCategoryRepository(
        dataSource: CategoryRemoteDataSource
    ) : CategoryRepository = CategoryRepositoryImpl(dataSource)
}