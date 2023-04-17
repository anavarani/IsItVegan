package com.varani.isitvegan.data.repository

import android.util.Log
import com.varani.isitvegan.TAG
import com.varani.isitvegan.data.local.dao.ProductDao
import com.varani.isitvegan.data.local.entities.ProductEntity
import com.varani.isitvegan.data.local.entities.toEntity
import com.varani.isitvegan.data.network.ProductNetworkDataSource
import com.varani.isitvegan.domain.model.Product
import com.varani.isitvegan.domain.model.asExternalModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFirstProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val network: ProductNetworkDataSource
) : ProductRepository {

    override suspend fun getProductByBarcode(barcode: String): Product? {
        if (productDao.getProductByBarcode(barcode) == null) {
            Log.d(TAG, "getProductByBarcode from network: $barcode")
            val barcodeDto = network.getProduct(barcode)
            productDao.insert(barcodeDto.product.toEntity())
        }
        return productDao.getProductByBarcode(barcode)?.asExternalModel()
    }

    override fun getAllProductsStream() = productDao.getAllProducts().map {
        it.map(ProductEntity::asExternalModel)
    }

    override suspend fun insertProduct(product: Product) = productDao.insert(product.toEntity())

    override suspend fun updateProduct(product: Product) = productDao.update(product.toEntity())

    override suspend fun deleteProduct(product: Product) = productDao.delete(product.toEntity())
}