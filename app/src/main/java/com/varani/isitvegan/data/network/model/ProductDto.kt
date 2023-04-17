package com.varani.isitvegan.data.network.model

import com.google.gson.annotations.SerializedName
import com.varani.isitvegan.data.network.ProductNetworkDataSource

/**
 * Created by Ana Varani on 15/04/2023.
 */
data class ProductDto(

    @SerializedName(ProductNetworkDataSource.ApiKeys.BARCODE)
    val barcode: String,

    @SerializedName(ProductNetworkDataSource.ApiKeys.INGREDIENTS_ANALYSIS_TAGS)
    val ingredientsAnalysisTags: ArrayList<String>,

    @SerializedName(ProductNetworkDataSource.ApiKeys.PRODUCT_IMAGE)
    val image: String,

    @SerializedName(ProductNetworkDataSource.ApiKeys.INGREDIENTS_ANALYSIS)
    val ingredientsAnalysis: IngredientAnalysisDto,
)

data class IngredientAnalysisDto(
    @SerializedName(ProductNetworkDataSource.ApiKeys.INGREDIENTS_ANALYSIS_NON_VEGAN)
    val nonVeganIngredients: ArrayList<String>?
)