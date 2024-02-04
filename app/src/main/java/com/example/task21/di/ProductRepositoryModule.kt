package com.example.task21.di

import com.example.task21.data.local.repository.LocalRepository
import com.example.task21.data.remote.repository.RemoteRepository
import com.example.task21.domain.local.repository.LocalProductRepository
import com.example.task21.domain.remote.repository.RemoteProductRepository
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
    fun bindRemoteProductRepository(dataSource: RemoteRepository): RemoteProductRepository

    @Binds
    @Singleton
    fun bindLocalProductRepository(localRepository: LocalRepository): LocalProductRepository
}