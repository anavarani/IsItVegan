package com.varani.isitvegan.di

import com.varani.isitvegan.data.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

/**
 * Created by Ana Varani on 03/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    fun provideProductApi(
        retrofit: Retrofit
    ): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}