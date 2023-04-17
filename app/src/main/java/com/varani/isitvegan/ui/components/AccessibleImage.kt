package com.varani.isitvegan.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Ana Varani on 17/04/2023.
 */
data class AccessibleImage(
    @DrawableRes val drawableId: Int,
    @StringRes val contentDescription: Int
)