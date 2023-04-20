package com.varani.isitvegan.ui.screen.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varani.isitvegan.R
import com.varani.isitvegan.common.HistoryMock

/**
 * Created by Ana Varani on 20/04/2023.
 */
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            HistoryUiState.Loading -> CircularProgressIndicator()
            is HistoryUiState.Products ->
                HistoryTabContent(
                    historyList = (uiState as HistoryUiState.Products).scanProducts,
                    onItemClick = { },
                    modifier = modifier.fillMaxSize(),
                )
            is HistoryUiState.Empty -> HistoryEmptyScreen()
        }
    }
}

@Composable
fun HistoryTabContent(
    historyList: List<History>,
    onItemClick: () -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        historyList.forEach { scannedItem ->
            val barcode = scannedItem.barcode
            item(key = barcode) {
                HistoryItem(
                    barcode = scannedItem.barcode,
                    onClick = { onItemClick },
                    isVegan = scannedItem.nonVeganIngredients.isEmpty(),
                    productImageUrl = scannedItem.photoUrl,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
fun HistoryItem(
    barcode: String,
    isVegan: Boolean,
    productImageUrl: String,
    iconModifier: Modifier = Modifier,
    modifier: Modifier,
    onClick: () -> Unit,
    itemSeparation: Dp = 16.dp,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 12.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = itemSeparation),
        ) {
            ProductPhoto(productImageUrl, iconModifier.size(64.dp))
            Spacer(modifier = Modifier.width(16.dp))
            ProductContent(barcode, isVegan)
        }
    }
}

@Composable
private fun ProductContent(name: String, isVegan: Boolean, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.body1,
        )
        Text(
            text = if (isVegan) "VEGAN" else "NOT VEGAN",
            style = MaterialTheme.typography.body2,
        )
    }
}

@Composable
fun ProductPhoto(productImageUrl: String, size: Modifier) {

}

@Composable
private fun HistoryEmptyScreen() {
    Text(text = stringResource(id = R.string.empty_history))
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun HistoryTabContentPreview() {
    HistoryTabContent(
        HistoryMock,
        {},
        Modifier.fillMaxSize()
    )
}