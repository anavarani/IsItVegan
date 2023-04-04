package com.varani.isitvegan.data

import com.varani.isitvegan.data.model.ProductDto

interface Repository {
    suspend fun getProduct(barcode: String): ProductDto
}