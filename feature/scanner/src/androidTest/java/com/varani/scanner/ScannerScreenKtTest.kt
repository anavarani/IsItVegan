package com.varani.scanner

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ana Varani on 04/05/2023.
 */
class ScannerScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicator_whenStateIsLoading() {
        composeTestRule.apply {
            setContent {
                ScannerScreen(
                    uiState = ScannerUiState.Loading,
                    onBarcodeRead = {},
                    onBarcodeReadError = {},
                )
            }

            onNodeWithTag(testTag = "loadingWheel").assertIsDisplayed()
        }
    }

    @Test
    fun onBarcodeReadFunction_whenScannerIsSuccess_isCalled() {
        val barcode = "123"
        var wasCalled = false

        composeTestRule.setContent {
            ScannerScreen(
                uiState = ScannerUiState.Success(barcode),
                onBarcodeRead = { wasCalled = true },
                onBarcodeReadError = {},
            )
        }

        assert(wasCalled)
    }

    @Test
    fun alertDialogComponent_whenScannerIsError_isShown() {

        val errorMessage = "Error"

        composeTestRule.setContent {
            ScannerScreen(
                uiState = ScannerUiState.Error(errorMessage),
                onBarcodeRead = { },
                onBarcodeReadError = { },
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

    @Test
    fun onBarcodeReadErrorFunction_whenScannerIsError_isCalled() {

        var wasCalled = false

        composeTestRule.setContent {
            ScannerScreen(
                uiState = ScannerUiState.Error("Error"),
                onBarcodeRead = { },
                onBarcodeReadError = { wasCalled = true },
            )
        }

        composeTestRule
            .onNodeWithText("OK")
            .performClick()
        assert(wasCalled)
    }
}