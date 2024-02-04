package com.example.task21.domain.remote.connectivity_observer

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Boolean>
}