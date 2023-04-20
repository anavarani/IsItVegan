package com.varani.isitvegan.navigation

import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.varani.isitvegan.ui.screen.history.HistoryScreen
import com.varani.isitvegan.ui.screen.home.HomeScreen
import com.varani.isitvegan.ui.screen.productDetail.ProductDetailScreen
import com.varani.isitvegan.ui.screen.scanner.ScannerScreen

/**
 * Created by Ana Varani on 13/04/2023.
 */
@ExperimentalGetImage
@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onScanBarcodeClick = { navController.navigate(Screen.Scanner.route) }
            )
        }
        composable(route = Screen.Scanner.route) {
            ScannerScreen(
                onBarcodeRead = { barcode ->
                    navController.navigate(Screen.History.createRoute(barcode))
                }
            )
        }
        composable(route = Screen.History.route) {
            HistoryScreen(
                onItemClick = { barcode ->
                    navController.navigate(Screen.ProductDetail.createRoute(barcode))
                }
            )
        }
        composable(route = Screen.ProductDetail.route) {
            ProductDetailScreen()
        }
    }
}