package com.app.supermarket.domain.di

import android.content.Context
import com.app.supermarket.base.AuthPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideAndroidContext(@ApplicationContext context: Context) : Context = context


    @Provides
    @Singleton
    fun provideSharedPrefHelper(context: Context) : AuthPreference = AuthPreference(context)
}