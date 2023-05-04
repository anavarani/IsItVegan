package com.varani.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Ana Varani on 04/05/2023.
 */
class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var disclaimer: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            disclaimer = getString(R.string.disclaimer)
        }
    }

    @Test
    fun disclaimerText_whenScreenIsLoaded_isShown() {
        composeTestRule.setContent {
            HomeScreen()
        }

        composeTestRule
            .onNodeWithText(disclaimer)
            .assertExists()
    }
}