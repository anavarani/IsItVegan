package com.varani.isitvegan.navigation

/**
 * Created by Ana Varani on 04/04/2023.
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Scanner : Screen("barcodeScanner")
    object History : Screen("history/{barcode}") {
        fun createRoute(barcode: String) = "history/$barcode"
    }

    object ProductDetail : Screen("productDetail/{barcode}") {
        fun createRoute(barcode: String) = "productDetail/$barcode"
    }
}