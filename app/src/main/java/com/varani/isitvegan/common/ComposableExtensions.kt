package com.varani.isitvegan.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Ana Varani on 19/04/2023.
 */
@Composable
fun Modifier.drawTopBorder(color: Color, isVisible: Boolean = true) =
    drawBehind {
        val borderSize = if (isVisible) 4.dp.toPx() else 0.dp.toPx()
        val y = borderSize / 2
        drawLine(
            color = color,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = borderSize
        )
    }

@Preview(showBackground = true)
@Composable
fun DrawTopBorderPreview() {
    Text(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.Gray)
            .drawTopBorder(MaterialTheme.colors.secondary),
        text = "Android",
        fontSize = 20.sp,
        color = MaterialTheme.colors.primary
    )
}