package com.varani.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.domain.GetProductByBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by Ana Varani on 15/04/2023.
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductByBarcodeUseCase: GetProductByBarcodeUseCase
) : ViewModel() {

    val uiState: StateFlow<ProductDetailUiState> =
        savedStateHandle.getStateFlow("barcode", "").map { barcode ->
            ProductDetailUiState.Success(getProductByBarcodeUseCase(barcode))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductDetailUiState.Loading,
        )
}