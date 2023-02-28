package com.app.supermarket.presentation.main.cart

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.data.models.response.CartItemResponse
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_cart

    private val cartItemAdapter: CartItemAdapter = CartItemAdapter()

    override fun initUI(savedInstanceState: Bundle?) {
        initAdapter()

    }

    private fun initAdapter() {
        val cartItems : MutableList<CartItemResponse> = mutableListOf()

        repeat(10) {
            val product = ProductResponse(1, localizedName = "Rice", true, 10, "Empty", "Empty",1, 10.0F, 20.0F, 10, 15, "https://www.world-grain.com/ext/resources/2022/10/25/Rice_AdobeStock_64819529_E.jpg?height=667&t=1666706505&width=1080", 1)
            cartItems.add(CartItemResponse(1, 20.0F, 18.0F, 1, 15, product))
        }


        binding.apply {
            rcvCartProducts.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    this.isSmoothScrolling
                }
                setHasFixedSize(false)
                cartItemAdapter.submitList(cartItems)
                adapter = cartItemAdapter
            }
        }
    }
}