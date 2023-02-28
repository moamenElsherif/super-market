package com.app.supermarket.presentation.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.response.CartItemResponse
import com.app.supermarket.databinding.CartProductItemBinding
import com.app.supermarket.presentation.product.productdetails.ProductDetailsListener

class CartItemAdapter : ListAdapter<CartItemResponse, CartItemAdapter.CartItemViewHolder>(DiffCallback<CartItemResponse>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    class CartItemViewHolder(private val binding: CartProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItemResponse) {
            binding.apply {
                item = cartItem
                executePendingBindings()
            }
        }

        companion object {
            fun form(parent: ViewGroup): CartItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartProductItemBinding.inflate(layoutInflater, parent, false)
                return CartItemViewHolder(binding)
            }
        }
    }
}