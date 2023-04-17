package com.varani.isitvegan.ui.screen.productDetail

sealed class ProductDetailUiState {

    object Loading : ProductDetailUiState()

    data class Success(
        val productDetail: ProductDetail
    ) : ProductDetailUiState()
}