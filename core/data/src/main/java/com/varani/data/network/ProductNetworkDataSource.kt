package com.varani.data.network

import com.varani.data.network.model.BarcodeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductNetworkDataSource {

    @GET("{barcode}.json")
    suspend fun getProduct(@Path("barcode") barcode: String): BarcodeDto

    object ApiKeys {
        const val PRODUCT = "product"
        const val BARCODE = "code"
        const val INGREDIENTS_ANALYSIS_TAGS = "ingredients_analysis_tags"
        const val INGREDIENTS_ANALYSIS = "ingredients_analysis"
        const val INGREDIENTS_ANALYSIS_NON_VEGAN = "en:non-vegan"
        const val PRODUCT_IMAGE = "image_front_url"
    }
}