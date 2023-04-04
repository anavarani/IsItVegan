package com.varani.isitvegan.data

import com.varani.isitvegan.data.model.BarcodeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("{barcode}.json")
    suspend fun getProduct(@Path("barcode") barcode: String): BarcodeDto
}