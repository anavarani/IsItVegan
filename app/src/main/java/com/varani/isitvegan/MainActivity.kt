package com.varani.isitvegan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.varani.isitvegan.navigation.AppNavGraph
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
    AppNavGraph(navController)
}