package com.varani.isitvegan.ui.screen.home

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

/**
 * Created by Ana Varani on 13/04/2023.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(onScanBarcodeClick: () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            val cameraPermissionState =
                rememberPermissionState(permission = Manifest.permission.CAMERA)

            Button(
                onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }
            ) {
                Text(text = "Camera Permission")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = onScanBarcodeClick) {
                Text(text = "Scan Barcode")
            }

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}