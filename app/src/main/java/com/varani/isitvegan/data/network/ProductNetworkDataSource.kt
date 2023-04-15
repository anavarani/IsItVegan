package com.varani.isitvegan.data.network

import com.varani.isitvegan.data.network.model.BarcodeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductNetworkDataSource {

    @GET("{barcode}.json")
    suspend fun getProduct(@Path("barcode") barcode: String): BarcodeDto

    object ApiKeys {
        const val INGREDIENTS_ANALYSIS_TAGS = "ingredients_analysis_tags"
        const val BARCODE = "code"
        const val PRODUCT = "product"
    }
}