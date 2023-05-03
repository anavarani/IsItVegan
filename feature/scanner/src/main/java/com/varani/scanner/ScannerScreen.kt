package com.varani.scanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Ana Varani on 13/04/2023.
 */
@Composable
fun ScannerScreen(
    uiState: ScannerUiState,
    onBarcodeRead: (String) -> Unit,
    onBarcodeReadError: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            ScannerUiState.Loading ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("loadingWheel")
                ) {
                    CircularProgressIndicator()
                }
            is ScannerUiState.Success -> onBarcodeRead(uiState.barcodeScanned)
            is ScannerUiState.Error -> AlertDialogComponent(
                onDismiss = onBarcodeReadError,
                message = uiState.message
            )
        }
    }
}

@Composable
fun ScannerRoute(
    onBarcodeRead: (String) -> Unit,
    onBarcodeReadError: () -> Unit,
    viewModel: ScannerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    ScannerScreen(uiState, onBarcodeRead, onBarcodeReadError)
}

// TODO to move to a UI module, fix parameters and make it reusable
@Composable
fun AlertDialogComponent(
    onDismiss: () -> Unit,
    message: String,
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },

            text = {
                Text(
                    text = message
                )
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onDismiss()
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}