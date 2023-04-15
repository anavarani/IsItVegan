package com.varani.isitvegan.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.varani.isitvegan.data.local.dao.ProductDao
import com.varani.isitvegan.data.local.entities.ProductEntity

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "food_database"
    }
}