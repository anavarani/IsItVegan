package com.varani.isitvegan.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.varani.history.navigation.historyScreen
import com.varani.history.navigation.navigateToHistory
import com.varani.home.navigation.homeNavigationRoute
import com.varani.home.navigation.homeScreen
import com.varani.home.navigation.navigateToHome
import com.varani.scanner.navigation.scannerScreen

/**
 * Created by Ana Varani on 13/04/2023.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    startDestination: String = homeNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen()
        scannerScreen(
            onBarcodeRead = { navController.navigateToHistory() },
            onBarcodeReadError = { navController.navigateToHome() },
        )
        historyScreen(onItemClick = {})
    }
}