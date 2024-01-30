package com.example.task21.presentation.event

sealed class ProductFragmentEvents {
    data object FetchProducts : ProductFragmentEvents()
    data object ResetErrorMessage : ProductFragmentEvents()
}