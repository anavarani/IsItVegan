package com.varani.scanner.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.varani.scanner.ScannerScreen

/**
 * Created by Ana Varani on 27/04/2023.
 */
const val scannerNavigationRoute = "scanner"

fun NavController.navigateToScanner(navOptions: NavOptions? = null) =
    this.navigate(scannerNavigationRoute, navOptions)

fun NavGraphBuilder.scannerScreen(
    onBackClick: () -> Unit,
    onBarcodeRead: (String) -> Unit
) {
    composable(route = scannerNavigationRoute) {
        ScannerScreen(onBackClick = onBackClick, onBarcodeRead = onBarcodeRead)
    }
}