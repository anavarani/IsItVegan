package com.varani.isitvegan.data.network.model

import com.google.gson.annotations.SerializedName
import com.varani.isitvegan.data.network.ProductNetworkDataSource

data class BarcodeDto(

    @SerializedName(ProductNetworkDataSource.ApiKeys.PRODUCT)
    val product: ProductDto
)