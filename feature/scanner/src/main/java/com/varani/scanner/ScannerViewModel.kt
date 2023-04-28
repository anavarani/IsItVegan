package com.varani.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.data.repository.OfflineFirstProductRepository
import com.varani.data.repository.ScannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scannerRepository: ScannerRepository,
    private val productRepository: OfflineFirstProductRepository,
) : ViewModel() {

    fun scanBarcode(onBackClick: () -> Unit, onBarcodeRead: (String) -> Unit) {
        viewModelScope.launch {
            scannerRepository.startScanning(onBackClick).collect {
                if (!it.isNullOrBlank()) {
                    updateDb(it)
                    onBarcodeRead(it)
                }
            }
        }
    }

    private fun updateDb(barcode: String) {
        viewModelScope.launch {
            productRepository.updateWithBarcode(barcode)
        }
    }
}