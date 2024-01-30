package com.example.task21.di

import com.example.task21.data.repository.CommonDataSource
import com.example.task21.domain.remote.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProductRepositoryModule {
    @Binds
    @Singleton
    fun bindProductRepository(repositoryImpl: CommonDataSource): ProductRepository
}