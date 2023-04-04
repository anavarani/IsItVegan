package com.varani.isitvegan.data.model

import com.google.gson.annotations.SerializedName
import com.varani.isitvegan.data.ApiFields

data class ProductDto(

    @SerializedName(ApiFields.Keys.INGREDIENTS_ANALYSIS_TAGS)
    val ingredientsAnalysisTags: ArrayList<String>
)