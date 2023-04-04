package com.varani.isitvegan.di

import com.varani.isitvegan.data.ProductRepository
import com.varani.isitvegan.data.Repository
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
    fun bindProductRepository(productRepository: ProductRepository): Repository
}