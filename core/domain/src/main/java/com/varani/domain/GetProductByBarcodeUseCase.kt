package com.varani.domain

import com.varani.data.repository.ProductRepository
import com.varani.domain.model.ProductDetail
import com.varani.domain.model.mapToProductDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
class GetProductByBarcodeUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(barcode: String): Flow<ProductDetail> {
        return productRepository.getProductByBarcode(barcode).mapToProductDetail()
    }
}