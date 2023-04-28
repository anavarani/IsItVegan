package com.varani.isitvegan.ui.screen.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import coil.compose.AsyncImage
import com.varani.isitvegan.R
import com.varani.isitvegan.common.HistoryMock
import com.varani.model.data.Product

/**
 * Created by Ana Varani on 20/04/2023.
 */
@Composable
fun HistoryScreen(
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            HistoryUiState.Loading ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            is HistoryUiState.Products ->
                HistoryTabContent(
                    historyList = (uiState as HistoryUiState.Products).scanProducts,
                    onItemClick = onItemClick,
                    modifier = modifier.fillMaxSize(),
                )
            is HistoryUiState.Empty -> HistoryEmptyScreen()
        }
    }
}

@Composable
fun HistoryTabContent(
    historyList: List<Product>,
    onItemClick: (String) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
    ) {
        items(
            count = historyList.size,
            key = { historyList[it].barcode },
            itemContent = { index ->
                val scannedItem = historyList[index]
                HistoryItem(
                    barcode = scannedItem.barcode,
                    onClick = { onItemClick(scannedItem.barcode) },
                    isVegan = scannedItem.nonVeganIngredients.isEmpty(),
                    productImageUrl = scannedItem.image,
                    modifier = modifier
                )
            }
        )
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
    itemSeparation: Dp = 12.dp,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small,
        tonalElevation = 12.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(itemSeparation),
        ) {
            ProductPhoto(productImageUrl, iconModifier.size(96.dp))
            Spacer(modifier = Modifier.width(16.dp))
            ProductContent(barcode, isVegan)
        }
    }
}

@Composable
private fun ProductContent(name: String, isVegan: Boolean, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = if (isVegan) "VEGAN" else "NOT VEGAN",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ProductPhoto(productImageUrl: String, size: Modifier) {
    Column(
        modifier = size,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = productImageUrl,
            contentDescription = null
        )
    }
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