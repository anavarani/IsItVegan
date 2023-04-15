package com.varani.isitvegan.data.local.dao

import androidx.room.*
import com.varani.isitvegan.data.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * from product WHERE barcode LIKE :barcode")
    suspend fun getProductByBarcode(barcode: String): ProductEntity?

    @Query("SELECT * from product ORDER BY barcode DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>
}