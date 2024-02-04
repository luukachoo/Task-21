package com.example.task21.presentation.event

sealed class ProductFragmentEvents {
    data object ResetErrorMessage : ProductFragmentEvents()
    data object CheckConnectivityStatusAndFetchProducts : ProductFragmentEvents()
    data class GetItemByCategory(val category: String) : ProductFragmentEvents()
    data object FetchItemsFromDatabase : ProductFragmentEvents()
}