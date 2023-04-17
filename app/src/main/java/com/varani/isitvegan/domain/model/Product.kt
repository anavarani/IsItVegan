package com.varani.isitvegan.domain.model

import com.varani.isitvegan.data.local.entities.ProductEntity

/**
 * Created by Ana Varani on 15/04/2023.
 */
data class Product(

    val barcode: String,
    val ingredientsAnalysisTags: ArrayList<String>,
    val image: String,
    val nonVeganIngredients: ArrayList<String>
)

fun ProductEntity.asExternalModel() = Product(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags,
    image = image,
    nonVeganIngredients = nonVeganIngredients
)