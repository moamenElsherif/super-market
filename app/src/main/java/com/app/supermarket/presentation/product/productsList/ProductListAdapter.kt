package com.app.supermarket.presentation.product.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.databinding.ProductItemBinding

class ProductListAdapter(
    private val categoryClickListener: AdapterClickListener<ProductResponse>
) : ListAdapter<ProductResponse, ProductListAdapter.ProductViewHolder>(DiffCallback<ProductResponse>()) {
    private var calculatedItemWidth : Int = 150

    fun setItemWidth(width: Int) {
        calculatedItemWidth = (width / 2) - 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(categoryClickListener, product, calculatedItemWidth)
    }

    class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AdapterClickListener<ProductResponse>, productResponse: ProductResponse, itemWidth: Int) {
            binding.apply {
//                cvProductCard.layoutParams.width = itemWidth
                productItem = productResponse
                listener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun form(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }
}