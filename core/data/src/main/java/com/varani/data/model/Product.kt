package com.varani.data.model

import com.varani.database.model.ProductEntity
import com.varani.network.model.ProductDto

/**
 * Created by Ana Varani on 28/04/2023.
 */
fun ProductDto.toEntity() = ProductEntity(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags ?: ArrayList(),
    image = image,
    nonVeganIngredients = ingredientsAnalysisDto?.nonVeganIngredients ?: ArrayList()
)