package com.varani.data.network.model

import com.google.gson.annotations.SerializedName
import com.varani.data.network.ProductNetworkDataSource

data class BarcodeDto(

    @SerializedName(ProductNetworkDataSource.ApiKeys.PRODUCT)
    val product: ProductDto
)