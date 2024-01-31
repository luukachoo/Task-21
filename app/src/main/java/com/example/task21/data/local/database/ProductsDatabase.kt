package com.example.task21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task21.data.local.dao.ProductDao
import com.example.task21.data.local.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductDao
}