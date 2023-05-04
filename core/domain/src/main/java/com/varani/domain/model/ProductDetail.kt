package com.varani.domain.model

import com.varani.common.AccessibleImage
import com.varani.domain.R
import com.varani.model.data.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ana Varani on 15/04/2023.
 */
data class ProductDetail(
    val photoUrl: String,
    val barcode: String,
    val veganClassification: AccessibleImage,
    val nonVeganIngredients: List<String>
)

fun Flow<Product>.mapToProductDetail(): Flow<ProductDetail> {
    return this.map { product ->
        ProductDetail(
            product.image,
            product.barcode,
            if (product.ingredientsAnalysisTags.contains("en:vegan")) { // TODO fix
                AccessibleImage(
                    R.drawable.vegan,
                    R.string.vegan_logo_content_description
                )
            } else if (product.ingredientsAnalysisTags.contains("en:non-vegan")) {
                AccessibleImage(
                    R.drawable.not_vegan,
                    R.string.not_vegan_logo_content_description
                )
            } else {
                AccessibleImage(
                    R.drawable.unknown,
                    R.string.unknown_logo_content_description
                )
            },
            product.nonVeganIngredients.map {
                it.substring(3)
            }
        )
    }
}