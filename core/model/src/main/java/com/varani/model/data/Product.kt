package com.varani.model.data

/**
 * Created by Ana Varani on 28/04/2023.
 */
data class Product(
    val barcode: String,
    val ingredientsAnalysisTags: ArrayList<String>,
    val image: String,
    val nonVeganIngredients: ArrayList<String>
) {
    fun getVeganClassification(): VeganClassification {
        return if (ingredientsAnalysisTags.contains("en:vegan")) {
            VeganClassification.VEGAN
        } else if (ingredientsAnalysisTags.contains("en:non-vegan")) {
            VeganClassification.NON_VEGAN
        } else if (ingredientsAnalysisTags.contains("en:maybe-vegan")) {
            VeganClassification.MAYBE
        } else {
            VeganClassification.UNKNOWN
        }
    }
}

enum class VeganClassification(val text: String) {
    VEGAN("Vegan"),
    NON_VEGAN("Non vegan"),
    MAYBE("Maybe vegan"),
    UNKNOWN("Unknown")
}