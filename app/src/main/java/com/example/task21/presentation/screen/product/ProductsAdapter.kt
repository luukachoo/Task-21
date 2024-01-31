package com.example.task21.presentation.screen.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task21.databinding.ItemProductBinding
import com.example.task21.presentation.model.Product

class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(ProductCallback()) {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) = with(binding) {
            Glide.with(itemView.context)
                .load(product.cover)
                .into(ivModel)
            tvTitle.text = product.title
            tvPrice.text = product.price

            if (product.favorite) {
                ivBackground.visibility = View.VISIBLE
                ivHeart.visibility = View.VISIBLE
            } else {
                ivBackground.visibility = View.GONE
                ivHeart.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}