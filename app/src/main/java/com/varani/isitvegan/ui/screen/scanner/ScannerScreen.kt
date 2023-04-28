package com.varani.isitvegan.ui.screen.scanner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Ana Varani on 13/04/2023.
 */
@Composable
fun ScannerScreen(
    onBackClick: () -> Unit,
    onBarcodeRead: (String) -> Unit,
    viewModel: ScannerViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.scanBarcode(onBackClick, onBarcodeRead)
    }
}