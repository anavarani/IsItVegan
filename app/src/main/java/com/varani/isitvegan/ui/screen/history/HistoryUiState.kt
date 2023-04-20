package com.varani.isitvegan.ui.screen.history

/**
 * Created by Ana Varani on 20/04/2023.
 */
sealed interface HistoryUiState {
    object Loading : HistoryUiState

    data class Products(
        val scanProducts: List<History>,
    ) : HistoryUiState

    object Empty : HistoryUiState
}