package com.novuspax.androidstoragewithapi.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getBaseUrl(): String = "https://rickandmortyapi.com/"

//    @Provides
//    @Singleton
//    fun providesRetrofit(): Retrofit {
//
//    }

}