package com.varani.isitvegan.ui.screen.scanner

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Ana Varani on 13/04/2023.
 */
@ExperimentalGetImage
@Composable
fun ScannerScreen(
    onBarcodeRead: (String) -> Unit,
    viewModel: ScannerViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.scanBarcode(onBarcodeRead)
    }
}