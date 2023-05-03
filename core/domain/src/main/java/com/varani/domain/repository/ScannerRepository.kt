package com.varani.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by Ana Varani on 03/05/2023.
 */
interface ScannerRepository {

    fun startScanning(): Flow<String?>
}