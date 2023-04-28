package com.varani.data.model

import com.varani.data.network.model.ProductDto
import com.varani.database.model.ProductEntity

/**
 * Created by Ana Varani on 28/04/2023.
 */
fun ProductDto.toEntity() = ProductEntity(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags ?: ArrayList(),
    image = image,
    nonVeganIngredients = ingredientsAnalysis?.nonVeganIngredients ?: ArrayList()
)