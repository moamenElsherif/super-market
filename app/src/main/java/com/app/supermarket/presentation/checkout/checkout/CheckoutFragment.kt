package com.app.supermarket.presentation.checkout.checkout

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.databinding.FragmentCheckoutBinding
import com.app.supermarket.domain.models.Checkout
import com.app.supermarket.domain.models.Product
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

        val checkout = requireActivity().intent.extras?.getParcelable<Checkout>(Constants.CHECKOUT_DATA_MODEL_KEY)

        checkout?.let { checkoutData ->
            viewModel.setCheckoutData(checkoutData)
        }

    }

    private fun initObservers() {
        viewModel.checkoutData.observe(viewLifecycleOwner) { checkoutData ->
            val productList : MutableList<Product> = checkoutData.cart.products.toMutableList()
            checkoutProductsAdapter.submitList(productList)
        }
    }

    private fun initRecycler() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = checkoutProductsAdapter
        }
    }

}