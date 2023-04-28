package com.varani.isitvegan.ui.screen.history

import com.varani.isitvegan.R
import com.varani.isitvegan.ui.components.AccessibleImage
import com.varani.model.data.Product

/**
 * Created by Ana Varani on 20/04/2023.
 */
data class History(
    val photoUrl: String,
    val barcode: String,
    val veganClassification: AccessibleImage,
    val nonVeganIngredients: List<String>
)

fun Product?.mapToHistoryItem(): History {
    return if (this != null) {
        History(
            image,
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
        History(
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