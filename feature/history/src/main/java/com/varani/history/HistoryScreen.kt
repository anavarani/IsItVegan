package com.varani.history

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.varani.model.data.Product
import com.varani.model.data.VeganClassification
import com.varani.model.data.testProductsList

/**
 * Created by Ana Varani on 20/04/2023.
 */
@Composable
fun HistoryRoute(
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    HistoryScreen(uiState, onItemClick, modifier)
}

@VisibleForTesting
@Composable
internal fun HistoryScreen(
    uiState: HistoryUiState,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            HistoryUiState.Loading ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("loadingWheel")
                ) {
                    CircularProgressIndicator()
                }
            is HistoryUiState.Success ->
                HistoryTabContent(
                    historyList = uiState.scanProducts,
                    onItemClick = onItemClick,
                    modifier = modifier.fillMaxSize(),
                )
        }
    }
}

@Composable
fun HistoryTabContent(
    historyList: List<Product>,
    onItemClick: (String) -> Unit,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
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
                    veganClassification = scannedItem.getVeganClassification(),
                    productImageUrl = scannedItem.image,
                )
            }
        )
    }
}

@Composable
fun HistoryItem(
    barcode: String,
    veganClassification: VeganClassification,
    productImageUrl: String,
    onClick: () -> Unit,
    itemSeparation: Dp = 12.dp,
) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.small,
        tonalElevation = 12.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(itemSeparation)
                .aspectRatio(0.6f, true),
        ) {
            ProductPhoto(productImageUrl, Modifier.size(96.dp))
            Spacer(modifier = Modifier.height(8.dp))
            ProductContent(barcode, veganClassification)
        }
    }
}

@Composable
private fun ProductContent(
    name: String,
    veganClassification: VeganClassification,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
        )
        Text(
            text = veganClassification.text,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun ProductPhoto(productImageUrl: String, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = productImageUrl,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 720)
@Composable
fun HistoryTabContentPreview() {
    HistoryTabContent(
        testProductsList,
        {},
        Modifier.fillMaxSize()
    )
}