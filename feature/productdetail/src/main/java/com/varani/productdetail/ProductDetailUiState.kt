package com.varani.productdetail

import com.varani.domain.model.ProductDetail

sealed class ProductDetailUiState {

    object Loading : ProductDetailUiState()

    data class Success(
        val productDetail: ProductDetail
    ) : ProductDetailUiState()
}