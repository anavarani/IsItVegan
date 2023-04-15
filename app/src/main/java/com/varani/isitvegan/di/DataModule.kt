package com.varani.isitvegan.di

import com.varani.isitvegan.data.repository.OfflineFirstProductRepository
import com.varani.isitvegan.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ana Varani on 04/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindProductRepository(offlineProductRepository: OfflineFirstProductRepository): ProductRepository
}