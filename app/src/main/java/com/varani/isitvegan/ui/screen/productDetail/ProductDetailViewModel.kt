package com.varani.isitvegan.ui.screen.productDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.isitvegan.domain.GetProductByBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ana Varani on 15/04/2023.
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getProductByBarcodeUseCase: GetProductByBarcodeUseCase
) : ViewModel() {

    private val barcode: String = checkNotNull(savedStateHandle["barcode"]) // TODO fix string hardcoded

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    init {
        viewModelScope.launch {
            val details = getProductByBarcodeUseCase(barcode)
            _uiState.value = ProductDetailUiState.Success(details)
        }
    }
}