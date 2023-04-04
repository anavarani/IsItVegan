package com.varani.isitvegan.data

import com.varani.isitvegan.data.model.ProductDto
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi
) : Repository {

    override suspend fun getProduct(barcode: String): ProductDto = api.getProduct(barcode).product
}