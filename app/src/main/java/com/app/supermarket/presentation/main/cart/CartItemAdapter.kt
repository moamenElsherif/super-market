package com.app.supermarket.presentation.main.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.base.callback.DiffCallback
import com.app.supermarket.data.models.cart.ProductResponse
import com.app.supermarket.databinding.CartProductItemBinding

class CartItemAdapter(private val cartListener: CartListener) :
    ListAdapter<ProductResponse, CartItemAdapter.CartItemViewHolder>(DiffCallback<ProductResponse>()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem, cartListener)
    }

    class CartItemViewHolder(private val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var initQuantity: Int = 1
        private var newQuantity: Int = 1

        fun bind(cartItem: ProductResponse, cartListener: CartListener) {
            binding.apply {
                item = cartItem
                listener = cartListener
                quantity = cartItem.quantity
                initQuantity = cartItem.quantity
                newQuantity = cartItem.quantity
                editVisibility = false
                setProductCount()
                btnMinus.setOnClickListener {
                    if (newQuantity == cartItem.minCounter) return@setOnClickListener
                    newQuantity -= 1
                    checkQuantityChange()
                    setProductCount()
                }
                btnPlus.setOnClickListener {
                    if (newQuantity == cartItem.maxCounter) return@setOnClickListener
                    newQuantity += 1
                    checkQuantityChange()
                    setProductCount()
                }
                cancelCheck.setOnClickListener {
                    newQuantity = initQuantity
                    checkQuantityChange()
                    setProductCount()
                }
                executePendingBindings()
            }
        }

        private fun checkQuantityChange() {
            binding.editVisibility = initQuantity != newQuantity
        }

        private fun setProductCount() {
            binding.quantity = newQuantity
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