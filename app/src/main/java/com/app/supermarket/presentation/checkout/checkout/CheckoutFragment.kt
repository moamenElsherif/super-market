package com.app.supermarket.presentation.checkout.checkout

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_checkout

    private val viewModel: CheckoutViewModel by viewModels()
    private val checkoutProductsAdapter: CheckoutItemAdapter =
        CheckoutItemAdapter(AdapterClickListener {

        })

    override fun initUI(savedInstanceState: Bundle?) {
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = checkoutProductsAdapter
        }
    }

    private fun initObservers() {
        viewModel.checkoutProducts.observe(viewLifecycleOwner) { products ->
            checkoutProductsAdapter.submitList(products)
        }
    }

}