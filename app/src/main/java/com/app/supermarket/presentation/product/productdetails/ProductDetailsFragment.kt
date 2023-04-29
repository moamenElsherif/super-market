package com.app.supermarket.presentation.product.productdetails

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.databinding.FragmentProductDetailsBinding
import com.app.supermarket.presentation.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(),
    ProductDetailsListener {
    override val layoutRes: Int
        get() = R.layout.fragment_product_details

    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()
    private var productDetailsUiState: ProductDetailsUiState? = null
    var productId: Int? = null

    private val productCount = MutableLiveData(1)

    @Inject
    lateinit var auth: Auth


    override fun initUI(savedInstanceState: Bundle?) {
        viewModel.getProducts(args.productId)
        binding.listener = this
        observeUiState()
        observeItemCount()
        handleBackPress()
    }

    private fun observeItemCount() {
        productCount.observe(this) {
            binding.productCount.text = it.toString()
        }
    }

    private fun handleBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.productResponse.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        productDetailsUiState = getUiState(resource.value.result)
                        binding.productItem = productDetailsUiState
                        productCount.value = productDetailsUiState?.minCounter!!
                        productId = productDetailsUiState?.id
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        Toast.makeText(
                            this@ProductDetailsFragment.requireContext(),
                            resource.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        hideLoading()
                    }
                }
            }
        }

        lifecycleScope.launch {
            viewModel.cartResponse.collectLatest { resource ->
                when (resource) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        createToast(R.string.successfully_added_to_cart)
                        hideLoading()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                    }
                    else -> {
                        hideLoading()
                    }
                }
            }
        }

    }

    private fun getUiState(it: ProductResponse): ProductDetailsUiState? {
        return ProductDetailsUiState(
            id = it.id,
            price = it.price,
            priceAfterDiscount = it.priceAfterDiscount,
            minCounter = it.minCounter,
            maxCounter = it.maxCounter,
            imageUrl = it.imageUrl,
            localizedName = it.localizedName,
            description = it.description,
            localizedDescription = it.localizedDescription,
            isActive = it.isActive,
            categoryId = it.categoryId,
            stockCount = it.stockCount
        )
    }

    override fun clickBack() {
        findNavController().popBackStack()
    }

    override fun clickCart() {
        Toast.makeText(this.requireContext(), "open cart", Toast.LENGTH_SHORT).show()
    }

    override fun clickPlus() {
        handlePlusClick()
    }

    override fun clickMinus() {
        handleMinusClick()
    }

    override fun clickOpenCart() {
        findNavController().navigate(ProductDetailsFragmentDirections.actionProductDetailsFragmentToCartBottomSheet())
    }

    override fun clickAddToCart() {
        if (!auth.getAccessToken().isNullOrEmpty()) {
            productId?.let { viewModel.addToCart(it, productCount.value!!) }
        } else {
            openAuthActivity()
        }
    }

    private fun openAuthActivity(){
        val intent = Intent(this.requireContext(), AuthenticationActivity::class.java)
        startActivity(intent)
    }

    private fun handlePlusClick() {
        if (productCount.value != productDetailsUiState?.maxCounter) {
            productCount.value = productCount.value!! + 1
        }
    }

    private fun handleMinusClick() {
        if (productCount.value != productDetailsUiState?.minCounter) {
            productCount.value = productCount.value!! - 1
        }
    }
}
