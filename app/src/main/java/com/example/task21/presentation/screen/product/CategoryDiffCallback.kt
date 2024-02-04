package com.example.task21.presentation.screen.product

import androidx.recyclerview.widget.DiffUtil

class CategoryDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem

}