package com.varani.isitvegan.domain

import com.varani.isitvegan.data.ProductRepository
import com.varani.isitvegan.data.model.ProductDto
import javax.inject.Inject

/**
 * Created by Ana Varani on 29/03/2023.
 */
class RetrieveProductDataUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(barcode: String): ProductDto {
        return productRepository.getProduct(barcode)
    }
}