package com.example.task21.di

import com.example.task21.data.local.data_source.LocalDataSource
import com.example.task21.data.remote.data_source.NetworkDataSource
import com.example.task21.data.repository.ProductRepositoryImpl
import com.example.task21.domain.local.repository.LocalProductRepository
import com.example.task21.domain.remote.repository.RemoteProductRepository
import com.example.task21.domain.repository.ProductRepository
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
    fun bindRemoteProductRepository(dataSource: NetworkDataSource): RemoteProductRepository

    @Binds
    @Singleton
    fun bindLocalProductRepository(localDataSource: LocalDataSource): LocalProductRepository

    @Binds
    @Singleton
    fun bindRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}