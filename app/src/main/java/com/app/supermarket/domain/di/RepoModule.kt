package com.app.supermarket.domain.di

import android.content.Context
import com.app.supermarket.base.AuthPreference
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.base.auth.SuperMarketAuth
import com.app.supermarket.data.datasource.CartDataSource
import com.app.supermarket.data.datasource.DataSource
import com.app.supermarket.data.repo.Repository
import com.app.supermarket.data.repo.RepositoryImpl
import com.app.supermarket.data.repo.cart.CartRepo
import com.app.supermarket.data.repo.cart.CartRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideCartRepository(
        dataSource: CartDataSource
    ) : CartRepo = CartRepoImpl(dataSource)


    @Provides
    @Singleton
    fun provideAuth(
        authPreference: AuthPreference
    ): Auth {
        return SuperMarketAuth(authPreference)
    }


}