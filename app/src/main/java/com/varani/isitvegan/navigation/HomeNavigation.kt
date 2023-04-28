package com.varani.isitvegan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.varani.isitvegan.ui.screen.home.HomeScreen

/**
 * Created by Ana Varani on 27/04/2023.
 */

const val homeNavigationRoute = "home"

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    this.navigate(homeNavigationRoute, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable(route = homeNavigationRoute) {
        HomeScreen()
    }
}