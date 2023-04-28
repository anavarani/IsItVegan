package com.varani.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.varani.history.HistoryScreen

/**
 * Created by Ana Varani on 27/04/2023.
 */
const val historyNavigationRoute = "history"

fun NavController.navigateToHistory(navOptions: NavOptions? = null) {
    this.navigate(historyNavigationRoute, navOptions)
}

fun NavGraphBuilder.historyScreen(onItemClick: (String) -> Unit) {
    composable(route = historyNavigationRoute) {
        HistoryScreen(onItemClick = onItemClick)
    }
}