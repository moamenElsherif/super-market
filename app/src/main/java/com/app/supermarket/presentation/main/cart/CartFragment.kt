package com.app.supermarket.presentation.main.cart

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants
import com.app.supermarket.base.Resource
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.base.auth.SuperMarketAuth
import com.app.supermarket.data.models.cart.MyCartResponse
import com.app.supermarket.databinding.FragmentCartBinding
import com.app.supermarket.presentation.checkout.CheckoutActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(),CartItemListener {
    override val layoutRes: Int
        get() = R.layout.fragment_cart

    private val cartItemAdapter: CartItemAdapter = CartItemAdapter(this)
    private val viewModel: CartViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        observeResponse()
        handleRefresh()
    }
    private fun handleRefresh() {
        binding.apply {
            swipeRefreshLayoutCategories.setColorSchemeColors(
                this@CartFragment.requireContext().getColor(R.color.orange)
            )
            swipeRefreshLayoutCategories.setOnRefreshListener {
                viewModel.getMyCart()
                Handler(Looper.myLooper()!!).postDelayed({
                    swipeRefreshLayoutCategories.isRefreshing = false
                }, Constants.SWIPE_REFRESH_TIME)
            }
        }

    }

    private fun observeResponse() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.cartResponse.collectLatest { response ->
                when (response) {
                    is Resource.Loading -> setRefreshingValue(true)
                    is Resource.Success -> {
                        setRefreshingValue(false)
                        initAdapter(response.value.result)
                    }
                    is Resource.Failure -> {
                        setRefreshingValue(false)
                        Toast.makeText(
                            this@CartFragment.requireContext(), response.message.toString(), Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        setRefreshingValue(false)
                    }
                }
            }
        }
    }

    private fun setRefreshingValue(value: Boolean) {
        binding.swipeRefreshLayoutCategories.isRefreshing = value
    }

    private fun initAdapter(result: MyCartResponse) {
        binding.apply {
            cartFragment = this@CartFragment

            rcvCartProducts.apply {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    this.isSmoothScrolling
                }
                setHasFixedSize(false)
                cartItemAdapter.submitList(result.products)
                adapter = cartItemAdapter
            }
        }
    }

    fun navigateToCheckout() {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun clickDelete(productId: Int) {
        viewModel.deleteFromCart(productId = productId)
    }

    override fun clickCheckOut() {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        startActivity(intent)
        requireActivity().finish()    }
}