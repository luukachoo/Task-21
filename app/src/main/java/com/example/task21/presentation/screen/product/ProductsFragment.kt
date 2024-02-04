package com.example.task21.presentation.screen.product

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task21.databinding.FragmentProductsBinding
import com.example.task21.presentation.common.base.BaseFragment
import com.example.task21.presentation.common.helper.Listener
import com.example.task21.presentation.common.helper.Observer
import com.example.task21.presentation.event.ProductFragmentEvents
import com.example.task21.presentation.extension.showSnackbar
import com.example.task21.presentation.state.ProductState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate),
    Listener,
    Observer {

    private val viewModel: ProductsViewModel by viewModels()
    private val productRecyclerAdapter by lazy { ProductsAdapter() }
    private val categoriesRecyclerAdapter by lazy { CategoryAdapter() }

    override fun init() {
        observers()
        setUpRecycler()
        listeners()
    }

    private fun setUpRecycler() = with(binding) {
        rvProducts.apply {
            adapter = productRecyclerAdapter
            viewModel.onEvent(ProductFragmentEvents.CheckConnectivityStatusAndFetchProducts)
        }

        rvCategory.apply {
            adapter = categoriesRecyclerAdapter
        }
    }

    override fun listeners() {
        categoriesRecyclerAdapter.onItemClick { x ->
            filterCategories(x)
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productState.collect {
                    handleProductsState(it)
                }
            }
        }
    }

    private fun filterCategories(category: String) =
        when (category) {
            "All" -> viewModel.onEvent(ProductFragmentEvents.FetchItemsFromDatabase)
            else -> viewModel.onEvent(ProductFragmentEvents.GetItemByCategory(category))
        }

    private fun handleProductsState(state: ProductState) = with(binding) {
        progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        state.products?.let {
            if (it.isEmpty()) {
                tvError.visibility = View.VISIBLE
            } else {
                productRecyclerAdapter.submitList(it)
                tvError.visibility = View.GONE
            }
        }

        state.categories?.let {
            categoriesRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            root.showSnackbar(it)
            viewModel.onEvent(ProductFragmentEvents.ResetErrorMessage)
        }
    }

}