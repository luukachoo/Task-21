package com.example.task21.presentation.screen.product

import android.view.View
import androidx.core.view.isVisible
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
class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate), Observer {

    private val viewModel: ProductsViewModel by viewModels()
    private val productRecyclerAdapter by lazy { ProductsAdapter() }

    override fun init() {
        observers()
        setUpRecycler()
    }

    private fun setUpRecycler() = with(binding) {
        rvProducts.adapter = productRecyclerAdapter
        viewModel.onEvent(ProductFragmentEvents.FetchProducts)
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

    private fun handleProductsState(state: ProductState) {
        binding.progressBar.isVisible = state.isLoading

        state.products?.let {
            if (it.isEmpty()) {
                binding.tvError.visibility = View.VISIBLE
            } else {
                productRecyclerAdapter.submitList(it)
                binding.tvError.visibility = View.GONE
            }
        }

        state.errorMessage?.let {
            binding.root.showSnackbar(it)
            viewModel.onEvent(ProductFragmentEvents.ResetErrorMessage)
        }
    }
}