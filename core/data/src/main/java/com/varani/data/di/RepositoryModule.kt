package com.varani.data.di

import com.varani.data.repository.LocalFirstProductRepository
import com.varani.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ana Varani on 28/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindProductRepository(offlineProductRepository: LocalFirstProductRepository): ProductRepository
}