package com.varani.domain.repository

import com.varani.model.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProductByBarcode(barcode: String): Flow<Product>

    fun getAllProductsStream(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun insertPendingProduct(barcode: String)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)

    suspend fun sync(product: Product)
}