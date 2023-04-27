package com.varani.isitvegan.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Ana Varani on 13/04/2023.
 */
@Composable
fun HomeScreen(onScanBarcodeClick: () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onScanBarcodeClick) {
                Text(text = "Scan Barcode")
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}