package com.varani.data.repository

import com.varani.data.model.toEntity
import com.varani.database.dao.ProductDao
import com.varani.database.model.ProductEntity
import com.varani.database.model.asExternalModel
import com.varani.database.model.toEntity
import com.varani.model.data.Product
import com.varani.network.ProductNetworkDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalFirstProductRepository @Inject constructor(
    private val productDao: ProductDao,
    private val network: ProductNetworkDataSource
) : ProductRepository {

    override suspend fun getProductByBarcode(barcode: String): Product? {
        updateWithBarcode(barcode)
        return productDao.getProductByBarcode(barcode)?.asExternalModel()
    }

    override suspend fun updateWithBarcode(barcode: String) {
        if (productDao.getProductByBarcode(barcode) == null) {
            val barcodeDto = network.getProduct(barcode)
            productDao.insert(barcodeDto.productDto.toEntity())
        }
    }

    override fun getAllProductsStream() = productDao.getAllProducts().map {
        it.map(ProductEntity::asExternalModel)
    }

    override suspend fun insertProduct(product: Product) = productDao.insert(product.toEntity())

    override suspend fun updateProduct(product: Product) = productDao.update(product.toEntity())

    override suspend fun deleteProduct(product: Product) = productDao.delete(product.toEntity())
}