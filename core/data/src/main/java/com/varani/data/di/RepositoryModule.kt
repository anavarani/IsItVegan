package com.varani.data.di

import com.varani.data.repository.GmsScannerRepository
import com.varani.data.repository.OfflineFirstProductRepository
import com.varani.domain.repository.ProductRepository
import com.varani.domain.repository.ScannerRepository
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
    fun bindProductRepository(offlineProductRepository: OfflineFirstProductRepository): ProductRepository

    @Binds
    fun bindScannerRepository(scannerRepository: GmsScannerRepository): ScannerRepository
}