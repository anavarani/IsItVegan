package com.varani.scanner

/**
 * Created by Ana Varani on 03/05/2023.
 */
sealed interface ScannerUiState {
    object Loading : ScannerUiState

    data class Success(
        val barcodeScanned: String,
    ) : ScannerUiState

    data class Error(
        val message: String,
    ) : ScannerUiState
}
