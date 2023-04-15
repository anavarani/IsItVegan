package com.varani.isitvegan.data.repository

import com.varani.isitvegan.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProductByBarcode(barcode: String): Product?

    fun getAllProductsStream(): Flow<List<Product>>

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}