package com.varani.isitvegan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.varani.isitvegan.ui.screen.scanner.ScannerScreen

/**
 * Created by Ana Varani on 27/04/2023.
 */
const val scannerNavigationRoute = "scanner"

fun NavController.navigateToScanner(navOptions: NavOptions? = null) =
    this.navigate(scannerNavigationRoute, navOptions)

fun NavGraphBuilder.scannerScreen(onBarcodeRead: (String) -> Unit) {
    composable(route = scannerNavigationRoute) {
        ScannerScreen(onBarcodeRead = onBarcodeRead)
    }
}