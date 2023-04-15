package com.varani.isitvegan.domain

import com.varani.isitvegan.data.repository.ProductRepository
import com.varani.isitvegan.domain.model.Product
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
class RetrieveProductDataUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(barcode: String): Product? {
        return productRepository.getProductByBarcode(barcode)
    }
}