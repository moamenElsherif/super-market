package com.app.supermarket.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.databinding.HomeCategoryItemBinding


class HomeCategoryAdapter(
    private val categoryClickListener: AdapterClickListener<Item>
) : ListAdapter<Item, HomeCategoryAdapter.CategoryViewHolder>(DiffCallback<Item>()) {
    private var calculatedItemWidth : Int = 150

    fun setItemWidth(width: Int) {
        calculatedItemWidth = (width / 2) - 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(categoryClickListener, category, calculatedItemWidth)
    }

    class CategoryViewHolder(private val binding: HomeCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AdapterClickListener<Item>, categoryResponse: Item, itemWidth: Int) {
            binding.apply {
                cvCategoryItem.layoutParams.width = itemWidth
                categoryItem = categoryResponse
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