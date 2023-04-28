package com.varani.isitvegan.ui.screen.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.data.model.toEntity
import com.varani.data.repository.ScannerRepository
import com.varani.database.dao.ProductDao
import com.varani.network.ProductNetworkDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scannerRepository: ScannerRepository,
    private val productDao: ProductDao,
    private val network: ProductNetworkDataSource
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

    private fun updateDb(it: String) {
        viewModelScope.launch {
            if (productDao.getProductByBarcode(it) == null) {
                val barcodeDto = network.getProduct(it)
                productDao.insert(barcodeDto.productDto.toEntity())
            }
        }
    }
}