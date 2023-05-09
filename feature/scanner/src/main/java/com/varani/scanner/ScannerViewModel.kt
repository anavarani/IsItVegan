package com.varani.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.domain.repository.ProductRepository
import com.varani.domain.repository.ScannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
@HiltViewModel
class ScannerViewModel @Inject constructor(
    scannerRepository: ScannerRepository,
    productRepository: ProductRepository
) : ViewModel() {

    val uiState: StateFlow<ScannerUiState> =
        scannerRepository.startScanning().map {
            if (!it.isNullOrEmpty()) {
                try {
                    productRepository.insertPendingProduct(barcode = it)
                    ScannerUiState.Success(it)
                } catch (e: Exception) {
                    ScannerUiState.Error("Failed to search for barcode. Please try again later")
                }
            } else {
                ScannerUiState.Error("Try again")
            }
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ScannerUiState.Loading,
            )
}