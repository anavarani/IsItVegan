package com.varani.data.repository

import com.varani.model.data.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductByBarcode(barcode: String): Flow<Product>

    suspend fun updateWithBarcode(barcode: String)

    fun getAllProductsStream(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}