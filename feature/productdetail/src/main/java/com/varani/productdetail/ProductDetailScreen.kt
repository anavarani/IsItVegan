package com.varani.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varani.common.AccessibleImage
import com.varani.domain.model.ProductDetail

/**
 * Created by Ana Varani on 13/04/2023.
 */
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    val productDetailUiState by viewModel.uiState.collectAsState()

    when (productDetailUiState) {
        ProductDetailUiState.Loading ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        is ProductDetailUiState.Success -> {
            ProductDetailCard((productDetailUiState as ProductDetailUiState.Success).productDetail)
        }
    }
}

@Composable
fun ProductDetailCard(productDetail: ProductDetail) {
    Surface(
        modifier = Modifier
            .padding(16.dp),
        shape = MaterialTheme.shapes.small,
        tonalElevation = 12.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp),
                    painter = painterResource(id = productDetail.veganClassification.drawableId),
                    contentDescription = stringResource(id = productDetail.veganClassification.contentDescription),
                    alignment = Alignment.Center
                )
            }

            Text(
                text = stringResource(id = R.string.barcode_title),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp
                )
            )
            Text(
                text = productDetail.barcode,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            if (productDetail.nonVeganIngredients.isNotEmpty()) {
                Text(
                    text = stringResource(id = R.string.non_vegan_ingredients_title),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = productDetail.nonVeganIngredients.joinToString("\n"),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ProductDetailCardPreview() {
    val productDetail = ProductDetail(
        "",
        "123123123123",
        AccessibleImage(
            com.varani.domain.R.drawable.not_vegan,
            R.string.non_vegan_ingredients_title
        ),
        listOf("milk", "egg")
    )

    ProductDetailCard(productDetail = productDetail)
}