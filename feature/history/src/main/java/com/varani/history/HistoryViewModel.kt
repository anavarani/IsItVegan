package com.varani.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.domain.GetAllScannedProductsUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by Ana Varani on 20/04/2023.
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    getAllScannedProductsUserCase: GetAllScannedProductsUserCase,
) : ViewModel() {

    val uiState: StateFlow<HistoryUiState> =
        getAllScannedProductsUserCase()
            .map {
                HistoryUiState.Products(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HistoryUiState.Loading,
            )
}