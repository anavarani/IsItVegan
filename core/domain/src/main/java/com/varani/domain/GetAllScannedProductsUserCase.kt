package com.varani.domain

import com.varani.domain.repository.ProductRepository
import com.varani.model.data.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ana Varani on 20/04/2023.
 */
class GetAllScannedProductsUserCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): Flow<List<Product>> {
        return productRepository.getAllProductsStream()
    }
}