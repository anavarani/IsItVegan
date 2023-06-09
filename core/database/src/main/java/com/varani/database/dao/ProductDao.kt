package com.varani.database.dao

import androidx.room.*
import com.varani.database.model.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Update
    suspend fun update(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)

    @Query("SELECT * from product WHERE barcode LIKE :barcode")
    fun getProductByBarcode(barcode: String): Flow<ProductEntity>

    @Query("SELECT * from product ORDER BY created_at DESC")
    fun getAllProducts(): Flow<List<ProductEntity>>
}