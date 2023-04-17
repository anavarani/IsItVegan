package com.varani.isitvegan.domain

import com.varani.isitvegan.data.repository.ProductRepository
import com.varani.isitvegan.ui.screen.productDetail.ProductDetail
import com.varani.isitvegan.ui.screen.productDetail.mapToProductDetail
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
class GetProductByBarcodeUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(barcode: String): ProductDetail {
        return productRepository.getProductByBarcode(barcode).mapToProductDetail()
    }
}