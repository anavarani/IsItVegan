package com.varani.data.repository

import android.util.Log
import com.varani.common.TAG
import com.varani.data.model.toEntity
import com.varani.database.dao.ProductDao
import com.varani.database.model.ProductEntity
import com.varani.database.model.asExternalModel
import com.varani.database.model.toEntity
import com.varani.domain.repository.ProductRepository
import com.varani.model.data.Product
import com.varani.network.ProductNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val network: ProductNetworkDataSource
) : ProductRepository {

    override fun getProductByBarcode(barcode: String): Flow<Product> {
        return productDao.getProductByBarcode(barcode).map { it.asExternalModel() }
    }

    override fun getAllProductsStream() = productDao.getAllProducts().map {
        it.forEach { product ->
            if (product.pendingSync) {
                sync(product.asExternalModel())
            }
        }
        it.map(ProductEntity::asExternalModel)
    }

    override suspend fun insertProduct(product: Product) = productDao.insert(product.toEntity())
    override suspend fun insertPendingProduct(barcode: String) =
        productDao.insert(ProductEntity(barcode, pendingSync = true))

    override suspend fun updateProduct(product: Product) = productDao.update(product.toEntity())

    override suspend fun deleteProduct(product: Product) = productDao.delete(product.toEntity())
    override suspend fun sync(product: Product) {
        try {
            network.getProduct(product.barcode)
                .also { barcodeDto ->
                    productDao.insert(barcodeDto.productDto.toEntity())
                }
        } catch (e: Exception) {
            Log.d(TAG, "sync: ${e.message}")
        }
    }
}