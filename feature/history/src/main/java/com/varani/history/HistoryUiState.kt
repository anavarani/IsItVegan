package com.varani.history

import com.varani.model.data.Product

/**
 * Created by Ana Varani on 20/04/2023.
 */
sealed interface HistoryUiState {
    object Loading : HistoryUiState

    data class Products(
        val scanProducts: List<Product>,
    ) : HistoryUiState

    object Empty : HistoryUiState
}