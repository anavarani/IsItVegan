package com.varani.isitvegan.di

import android.content.Context
import androidx.room.Room
import com.varani.isitvegan.data.local.FoodDatabase
import com.varani.isitvegan.data.local.FoodDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ana Varani on 15/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room.databaseBuilder(
            context,
            FoodDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: FoodDatabase) = database.productDao()
}