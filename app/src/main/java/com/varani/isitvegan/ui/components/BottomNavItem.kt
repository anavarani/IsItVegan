package com.varani.isitvegan.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Ana Varani on 18/04/2023.
 */
data class BottomNavItem(
    @StringRes val name: Int,
    val route: String,
    @DrawableRes val icon: Int,
    val badgeCount: Int = 0
)
