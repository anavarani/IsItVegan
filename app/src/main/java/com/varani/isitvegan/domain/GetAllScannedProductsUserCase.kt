package com.varani.isitvegan.domain

import com.varani.isitvegan.data.repository.ProductRepository
import com.varani.isitvegan.ui.screen.history.History
import com.varani.isitvegan.ui.screen.history.mapToHistoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ana Varani on 20/04/2023.
 */
class GetAllScannedProductsUserCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    operator fun invoke(): Flow<List<History>> {
        return productRepository.getAllProductsStream().map { list ->
            list.map { product ->
                product.mapToHistoryItem()
            }
        }
    }
}