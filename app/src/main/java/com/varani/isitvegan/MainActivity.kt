package com.varani.isitvegan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.varani.isitvegan.navigation.AppNavGraph
import com.varani.isitvegan.navigation.Screen
import com.varani.isitvegan.ui.components.BottomNavItem
import com.varani.isitvegan.ui.components.BottomNavigationBar
import com.varani.isitvegan.ui.theme.IsItVeganTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalGetImage
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContent {
            IsItVeganTheme {
                IsItVeganApp()
            }
        }
    }
}

@ExperimentalGetImage
@Composable
fun IsItVeganApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items, navController) {
                navController.navigate(it.route)
            }
        }
    ) { innerPadding ->
        AppNavGraph(navController, Modifier.padding(innerPadding))
    }
}

val items = listOf(
    BottomNavItem(
        name = R.string.home_tab,
        route = Screen.Home.route,
        icon = R.drawable.baseline_home_24
    ),
    BottomNavItem(
        name = R.string.scan_tab,
        route = Screen.Scanner.route,
        icon = R.drawable.baseline_qr_code_scanner_24
    ),
)