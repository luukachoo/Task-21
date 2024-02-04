package com.example.task21.presentation.screen.product

import androidx.recyclerview.widget.DiffUtil
import com.example.task21.presentation.model.Product

class ProductCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
}