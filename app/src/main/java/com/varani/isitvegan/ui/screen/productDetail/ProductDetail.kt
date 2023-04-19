package com.varani.isitvegan.ui.screen.productDetail

import com.varani.isitvegan.R
import com.varani.isitvegan.domain.model.Product
import com.varani.isitvegan.ui.components.AccessibleImage

/**
 * Created by Ana Varani on 15/04/2023.
 */
data class ProductDetail(
    val photoUrl: String,
    val barcode: String,
    val veganClassification: AccessibleImage,
    val nonVeganIngredients: List<String>
)

fun Product?.mapToProductDetail(): ProductDetail {
    return if (this != null) {
        ProductDetail(
            "",
            barcode,
            if (ingredientsAnalysisTags.contains("en:vegan")) { // TODO fix
                AccessibleImage(
                    R.drawable.vegan,
                    R.string.vegan_logo_content_description
                )
            } else if (ingredientsAnalysisTags.contains("en:non-vegan")) {
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
            nonVeganIngredients.map {
                it.substring(3)
            }
        )
    } else {
        ProductDetail(
            "",
            "123",
            // TODO temporary default product
            AccessibleImage(
                R.drawable.unknown,
                R.string.unknown_logo_content_description
            ),
            listOf()
        )
    }
}