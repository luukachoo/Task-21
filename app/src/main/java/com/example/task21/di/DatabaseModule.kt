package com.example.task21.di

import android.content.Context
import androidx.room.Room
import com.example.task21.data.local.database.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): ProductsDatabase =
        Room.databaseBuilder(
            context, ProductsDatabase::class.java, "PRODUCTS_DATABASE"
        )
            .build()

    @Provides
    @Singleton
    fun provideDao(db: ProductsDatabase) = db.productsDao()
}