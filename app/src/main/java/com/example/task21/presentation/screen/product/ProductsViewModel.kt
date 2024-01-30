package com.example.task21.presentation.screen.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task21.data.remote.util.Resource
import com.example.task21.domain.remote.use_case.GetProductUseCase
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
class ProductsViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) :
    ViewModel() {

    private val _productState = MutableStateFlow(ProductState())
    val productState = _productState.asStateFlow()


    fun onEvent(event: ProductFragmentEvents) {
        when (event) {
            ProductFragmentEvents.FetchProducts -> fetchProducts()
            ProductFragmentEvents.ResetErrorMessage -> updateErrorMessage(null)
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            getProductUseCase().collect { resource ->
                _productState.update { it.copy(isLoading = true) }
                when (resource) {
                    is Resource.Success -> {
                        _productState.update { currentState ->
                            currentState.copy(
                                products = resource.data.map { it.toPresentation() },
                                isLoading = false,
                            )
                        }
                    }

                    is Resource.Error -> {
                        updateErrorMessage(resource.errorMessage)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _productState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}