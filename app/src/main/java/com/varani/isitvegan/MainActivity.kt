package com.varani.isitvegan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.varani.isitvegan.navigation.AppNavGraph
import com.varani.isitvegan.navigation.AppNavState
import com.varani.isitvegan.ui.components.BottomNavigationBar
import com.varani.isitvegan.ui.theme.IsItVeganTheme
import dagger.hilt.android.AndroidEntryPoint

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsItVeganApp() {
    val navController = rememberNavController()
    val appNavState = AppNavState(navController)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                appNavState.destinations,
                appNavState::navigateToDestination,
                appNavState.currentDestination
            )
        }
    ) { innerPadding ->
        AppNavGraph(navController, Modifier.padding(innerPadding))
    }
}