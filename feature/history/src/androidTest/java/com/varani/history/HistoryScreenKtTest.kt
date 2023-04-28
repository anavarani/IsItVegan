package com.varani.history

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.varani.model.data.testProductsList
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ana Varani on 28/04/2023.
 */
class HistoryScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingIndicator_whenStateIsLoading() {
        composeTestRule.apply {
            setContent {
                HistoryScreen(
                    uiState = HistoryUiState.Loading,
                    onItemClick = {},
                )
            }

            onNodeWithTag(testTag = "loadingWheel").assertIsDisplayed()
        }
    }

    @Test
    fun topicTitle_whenTopicIsSuccess_isShown() {
        composeTestRule.setContent {
            HistoryScreen(
                uiState = HistoryUiState.Success(testProductsList),
                onItemClick = {},
            )
        }

        composeTestRule
            .onNodeWithText(testProductsList.first().barcode)
            .assertExists()
    }
}