package com.example.task21.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task21.data.remote.util.Resource
import com.example.task21.data.util.NetworkConnectivityObserver
import com.example.task21.domain.remote.use_case.ProductsUseCase
import com.example.task21.presentation.event.ProductFragmentEvents
import com.example.task21.presentation.mapper.toPresentation
import com.example.task21.presentation.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val connectivityObserver: NetworkConnectivityObserver,
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()

    fun onEvent(event: ProductFragmentEvents) {
        when (event) {
            is ProductFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
            is ProductFragmentEvents.CheckConnectivityStatusAndFetchProducts -> checkConnectivityStatus()
            is ProductFragmentEvents.GetItemByCategory -> filterItemsByCategory(event.category)
            ProductFragmentEvents.FetchItemsFromDatabase -> fetchProductsFromDatabase()
        }
    }

    private fun checkConnectivityStatus() {
        viewModelScope.launch {
            connectivityObserver.observe().collect { isConnected ->
                if (isConnected) {
                    fetchProducts()
                } else {
                    fetchProductsFromDatabase()
                }
            }
        }
    }

    private fun fetchProductsFromDatabase() {
        viewModelScope.launch {
            productsUseCase.getProductsFromDatabase().collect { list ->
                val categories = list.map { it.category }.toSet().toList()
                val updatedCategories = mutableListOf("All").apply { addAll(categories) }

                _productState.update { currentState ->
                    currentState.copy(
                        products = list.map { it.toPresentation() },
                        isLoading = false,
                        categories = updatedCategories
                    )
                }

            }
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            productsUseCase.getProductsFromRemote().collect { resource ->
                _productState.update { it.copy(isLoading = true) }
                when (resource) {
                    is Resource.Success -> {
                        val categories = resource.data.map { it.category }.toSet().toList()
                        val updatedCategories = mutableListOf("All").apply { addAll(categories) }

                        _productState.update { currentState ->
                            currentState.copy(
                                products = resource.data.map { it.toPresentation() },
                                isLoading = false,
                                categories = updatedCategories
                            )
                        }

                        productsUseCase.insertProductsToDatabase(resource.data)
                    }

                    is Resource.Error -> {
                        updateErrorMessage(resource.errorMessage)
                    }
                }
            }
        }
    }

    private fun filterItemsByCategory(category: String) {
        viewModelScope.launch {
            productsUseCase.getProductByCategory(category).collect { list ->

                _productState.update { currentState ->
                    currentState.copy(
                        products = list.map { product ->
                            product.toPresentation()
                        },
                        isLoading = false,
                    )
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _productState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}