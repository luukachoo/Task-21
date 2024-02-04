package com.example.task21.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class NetworkConnectivityObserver @Inject constructor(@ApplicationContext private val context: Context) :
    ConnectivityObserver {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableStateFlow(false)


    override fun observe(): Flow<Boolean> {
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                _isConnected.value = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                _isConnected.value = false
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)

        return _isConnected
    }


    init {
        observe()
    }
}