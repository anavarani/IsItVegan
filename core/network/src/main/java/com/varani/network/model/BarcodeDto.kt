package com.varani.network.model

import com.google.gson.annotations.SerializedName
import com.varani.network.ProductNetworkDataSource

data class BarcodeDto(

    @SerializedName(ProductNetworkDataSource.ApiKeys.PRODUCT)
    val productDto: ProductDto
)