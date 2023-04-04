package com.varani.isitvegan.data.model

import com.google.gson.annotations.SerializedName
import com.varani.isitvegan.data.ApiFields

data class BarcodeDto(

    @SerializedName(ApiFields.Keys.PRODUCT)
    val product: ProductDto
)