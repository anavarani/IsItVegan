package com.varani.productdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.domain.GetProductByBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Ana Varani on 15/04/2023.
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductByBarcodeUseCase: GetProductByBarcodeUseCase
) : ViewModel() {

    @OptIn(FlowPreview::class)
    val uiState: StateFlow<ProductDetailUiState> =
        savedStateHandle.getStateFlow("barcode", "").flatMapConcat { barcode ->
            getProductByBarcodeUseCase(barcode).map {
                ProductDetailUiState.Success(it)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProductDetailUiState.Loading,
        )
}