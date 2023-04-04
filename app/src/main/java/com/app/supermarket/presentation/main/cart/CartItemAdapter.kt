package com.app.supermarket.presentation.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.cart.ProductResponse
import com.app.supermarket.databinding.CartProductItemBinding

class CartItemAdapter(private val cartListener: CartItemListener) :
    ListAdapter<ProductResponse, CartItemAdapter.CartItemViewHolder>(DiffCallback<ProductResponse>()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem , cartListener)
    }

    class CartItemViewHolder(private val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var quantity: Int = 1

        fun bind(cartItem: ProductResponse, cartListener: CartItemListener) {
            binding.apply {
                item = cartItem
                listener = cartListener
                quantity = cartItem.quantity
                setProductCount()
                btnMinus.setOnClickListener {
                    if (quantity == cartItem.minCounter) return@setOnClickListener
                    quantity -= 1
                    setProductCount()
                }
                btnPlus.setOnClickListener {
                    if (quantity == cartItem.maxCounter) return@setOnClickListener
                    quantity += 1
                    setProductCount()
                }
                executePendingBindings()
            }
        }

        private fun setProductCount() {
            binding.productCount.text = quantity.toString()
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