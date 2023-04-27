package com.app.supermarket.presentation.checkout.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.databinding.CheckoutProductItemBinding
import com.app.supermarket.domain.models.Product


class CheckoutItemAdapter(
    private val productClickListener: AdapterClickListener<Product>
) : ListAdapter<Product, CheckoutItemAdapter.CheckoutItemViewHolder>(DiffCallback<Product>()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutItemViewHolder {
        return CheckoutItemViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CheckoutItemViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(productClickListener, product)
    }

    class CheckoutItemViewHolder(private val binding: CheckoutProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: AdapterClickListener<Product>, productItem: Product) {
            binding.apply {
                product = productItem
                executePendingBindings()
            }
        }

        companion object {
            fun form(parent: ViewGroup): CheckoutItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CheckoutProductItemBinding.inflate(layoutInflater, parent, false)
                return CheckoutItemViewHolder(binding)
            }
        }
    }
}