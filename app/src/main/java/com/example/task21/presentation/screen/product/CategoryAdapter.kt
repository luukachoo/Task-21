package com.example.task21.presentation.screen.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task21.databinding.ItemCategoryBinding

class CategoryAdapter :
    ListAdapter<String, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var onCategoryClick: ((String) -> Unit)? = null

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String) = with(binding) {
            buttonCategory.text = category
            buttonCategory.setOnClickListener {
                onCategoryClick?.invoke(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onItemClick(click: (String) -> Unit) {
        this.onCategoryClick = click
    }
}