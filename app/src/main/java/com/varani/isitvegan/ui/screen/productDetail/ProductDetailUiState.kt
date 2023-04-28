package com.varani.isitvegan.ui.screen.productDetail

import com.varani.domain.model.ProductDetail

sealed class ProductDetailUiState {

    object Loading : ProductDetailUiState()

    data class Success(
        val productDetail: ProductDetail
    ) : ProductDetailUiState()
}