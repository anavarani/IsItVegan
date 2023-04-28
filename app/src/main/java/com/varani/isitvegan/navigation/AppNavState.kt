package com.varani.isitvegan.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.varani.history.navigation.navigateToHistory
import com.varani.home.navigation.navigateToHome
import com.varani.isitvegan.R
import com.varani.scanner.navigation.navigateToScanner

class AppNavState(
    private val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val destinations: List<AppNavDestination> = AppNavDestination.values().asList()

    fun navigateToDestination(destination: AppNavDestination) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id)
            launchSingleTop = true
        }

        when (destination) {
            AppNavDestination.HOME -> navController.navigateToHome(navOptions = navOptions)
            AppNavDestination.SCANNER -> navController.navigateToScanner(navOptions = navOptions)
            AppNavDestination.HISTORY -> navController.navigateToHistory(navOptions = navOptions)
        }
    }
}

/**
 * Created by Ana Varani on 04/04/2023.
 */
enum class AppNavDestination(
    @DrawableRes val iconResId: Int,
    @StringRes val textResId: Int,
) {
    HOME(
        iconResId = R.drawable.home_tab,
        textResId = R.string.home_tab
    ),
    SCANNER(
        iconResId = R.drawable.barcode_scanner_tab,
        textResId = R.string.scan_tab
    ),
    HISTORY(
        iconResId = R.drawable.history_tab,
        textResId = R.string.history_tab
    ),
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: AppNavDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false