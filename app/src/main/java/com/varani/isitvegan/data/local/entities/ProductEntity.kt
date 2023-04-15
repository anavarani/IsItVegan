package com.varani.isitvegan.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varani.isitvegan.data.local.entities.ProductEntity.Companion.PRODUCT_TABLE
import com.varani.isitvegan.data.network.model.ProductDto
import com.varani.isitvegan.domain.model.Product

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Entity(tableName = PRODUCT_TABLE)
data class ProductEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = BARCODE_COLUMN)
    val barcode: String,

    @ColumnInfo(name = INGREDIENTS_TAGS_COLUMN)
    val ingredientsAnalysisTags: ArrayList<String>
) {
    companion object {
        const val PRODUCT_TABLE = "product"
        const val BARCODE_COLUMN = "barcode"
        const val INGREDIENTS_TAGS_COLUMN = "ingredients_tags"
    }
}

fun ProductDto.toEntity() = ProductEntity(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags
)

fun Product.toEntity() = ProductEntity(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags
)