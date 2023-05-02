package com.varani.testing.repository

import com.varani.data.repository.ProductRepository
import com.varani.model.data.Product
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

/**
 * Created by Ana Varani on 28/04/2023.
 */
class TestProductRepository : ProductRepository {

    private val productFlow: MutableSharedFlow<List<Product>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getProductByBarcode(barcode: String): Flow<Product> {
        return productFlow.map { product -> product.find { it.barcode == barcode }!! }
    }

    override suspend fun updateWithBarcode(barcode: String) {
        TODO("Not yet implemented")
    }

    override fun getAllProductsStream(): Flow<List<Product>> {
        return productFlow
    }

    override suspend fun insertProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(product: Product) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(product: Product) {
        TODO("Not yet implemented")
    }

    fun sendProducts(products: List<Product>) {
        productFlow.tryEmit(products)
    }
}