package com.varani.isitvegan.ui.screen.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.isitvegan.data.local.dao.ProductDao
import com.varani.isitvegan.data.local.entities.toEntity
import com.varani.isitvegan.data.network.ProductNetworkDataSource
import com.varani.isitvegan.data.repository.ScannerRepository
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

    fun scanBarcode(onBarcodeRead: (String) -> Unit) {
        viewModelScope.launch {
            scannerRepository.startScanning().collect {
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
                productDao.insert(barcodeDto.product.toEntity())
            }
        }
    }
}