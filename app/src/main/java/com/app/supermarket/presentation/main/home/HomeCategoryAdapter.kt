package com.app.supermarket.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.response.CategoryResponse
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.databinding.HomeCategoryItemBinding


class HomeCategoryAdapter(
    private val categoryClickListener: AdapterClickListener<CategoryResponse>
) : ListAdapter<CategoryResponse, HomeCategoryAdapter.CategoryViewHolder>(DiffCallback<CategoryResponse>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(categoryClickListener, category)
    }

    class CategoryViewHolder(private val binding: HomeCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AdapterClickListener<CategoryResponse>, categoryResponse: CategoryResponse) {
            binding.apply {
                binding.categoryItem = categoryResponse
                listener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun form(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HomeCategoryItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }
    }
}