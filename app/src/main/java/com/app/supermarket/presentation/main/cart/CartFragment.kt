package com.app.supermarket.presentation.main.cart

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants
import com.app.supermarket.base.Resource
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.data.models.cart.MyCartResponse
import com.app.supermarket.databinding.FragmentCartBinding
import com.app.supermarket.domain.models.Checkout
import com.app.supermarket.presentation.authentication.AuthenticationActivity
import com.app.supermarket.presentation.checkout.CheckoutActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(), CartListener {
    override val layoutRes: Int
        get() = R.layout.fragment_cart

    @Inject
    lateinit var auth: Auth

    private val cartItemAdapter: CartItemAdapter = CartItemAdapter(this)
    private val viewModel: CartViewModel by viewModels()
    private var cartItems: MyCartResponse? = null

    override fun initUI(savedInstanceState: Bundle?) {
        binding.listener = this
        observeResponse()
        handleRefresh()
        getCartItems()
        observeCartUpdate()
    }

    private fun getCartItems() {
        if (auth.isLoggedIn()) viewModel.getMyCart()
        handleLoginVisibility(auth.isLoggedIn())
    }

    private fun handleLoginVisibility(loggedIn: Boolean) {
            cartLoginVisibility(loggedIn)
    }

    private fun cartLoginVisibility(loggedIn: Boolean) {
        binding.loginView.isVisible = !loggedIn
        binding.swipeRefreshLayoutCategories.isVisible = loggedIn
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
                        cartItems = response.value.result
                    }
                    is Resource.Failure -> {
                        setRefreshingValue(false)
                        Toast.makeText(
                            this@CartFragment.requireContext(),
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        setRefreshingValue(false)
                    }
                }
            }
        }
    }

    private fun observeCartUpdate() {
        lifecycleScope.launch {
            viewModel.updateCartResponse.collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        viewModel.getMyCart()
                    }
                    is Resource.Failure -> {
                        createToast(R.string.update_failed)
                    }
                    else -> {
                    }
                }
            }
        }

    }

    private fun setRefreshingValue(value: Boolean) {
        binding.swipeRefreshLayoutCategories.isRefreshing = value
    }

    private fun initAdapter(result: MyCartResponse) {
        if (result.products.isEmpty()) {
            cartItemsVisibility(true)
        }
        else {
            cartItemsVisibility(false)
            binding.apply {
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
    }

    private fun cartItemsVisibility(empty: Boolean) {
        binding.emptyCartView.isVisible = empty
        binding.rcvCartProducts.isVisible = !empty
    }

    override fun clickDelete(productId: Int) {
        viewModel.deleteFromCart(productId = productId)
    }

    override fun clickCheckOut() {
        val intent = Intent(requireContext(), CheckoutActivity::class.java)
        val checkoutData = cartItems?.toCartData()?.let { Checkout(0, it) }
        intent.putExtra(Constants.CHECKOUT_DATA_MODEL_KEY, checkoutData)
        startActivity(intent)
    }

    override fun clickSave(productId: Int, itemCount: Int) {
        viewModel.updateCart(productId, itemCount)
    }

    override fun clickLogin() {
        startActivity(Intent(this.requireContext(), AuthenticationActivity::class.java))
        this.requireActivity().finish()
    }

    override fun clickOpenCategories() {
        findNavController().navigate(R.id.homeFragment)
    }

}