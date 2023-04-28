package com.varani.model.data

/**
 * Created by Ana Varani on 28/04/2023.
 */
data class Product(
    val barcode: String,
    val ingredientsAnalysisTags: ArrayList<String>,
    val image: String,
    val nonVeganIngredients: ArrayList<String>
)