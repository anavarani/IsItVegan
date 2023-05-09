package com.varani.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.varani.database.model.ProductEntity.Companion.PRODUCT_TABLE
import com.varani.model.data.Product

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Entity(tableName = PRODUCT_TABLE)
data class ProductEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = BARCODE_COLUMN)
    val barcode: String,

    @ColumnInfo(name = INGREDIENTS_TAGS_COLUMN)
    val ingredientsAnalysisTags: ArrayList<String> = arrayListOf(),

    @ColumnInfo(name = PRODUCT_IMAGE_URL_COLUMN)
    val image: String = "",

    @ColumnInfo(name = NON_VEGAN_INGREDIENTS_COLUMN)
    val nonVeganIngredients: ArrayList<String> = arrayListOf(),

    @ColumnInfo(name = PENDING_SYNC)
    val pendingSync: Boolean = false,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Long = System.currentTimeMillis(),
) {
    companion object {
        const val PRODUCT_TABLE = "product"
        const val BARCODE_COLUMN = "barcode"
        const val INGREDIENTS_TAGS_COLUMN = "ingredients_tags"
        const val NON_VEGAN_INGREDIENTS_COLUMN = "non_vegan_ingredients"
        const val PRODUCT_IMAGE_URL_COLUMN = "product_image_url"
        const val PENDING_SYNC = "pending_sync"
        const val CREATED_AT = "created_at"
    }
}

fun ProductEntity.asExternalModel() = Product(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags,
    image = image,
    nonVeganIngredients = nonVeganIngredients
)

fun Product.toEntity() = ProductEntity(
    barcode = barcode,
    ingredientsAnalysisTags = ingredientsAnalysisTags,
    image = image,
    nonVeganIngredients = nonVeganIngredients
)