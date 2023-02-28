package com.app.supermarket.presentation.product.productdetails

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
import com.app.supermarket.data.models.response.ProductResponse
import com.app.supermarket.databinding.FragmentProductDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailsFragment : BaseFragment<FragmentProductDetailsBinding>(),
    ProductDetailsListener {
    override val layoutRes: Int
        get() = R.layout.fragment_product_details

    private val args: ProductDetailsFragmentArgs by navArgs()
    private val viewModel: ProductDetailsViewModel by viewModels()
    private var productDetailsUiState: ProductDetailsUiState? = null

    private val productCount = MutableLiveData<Int>(1)


    override fun initUI(savedInstanceState: Bundle?) {
        viewModel.getProducts(args.productId)
        binding.listener = this
        observeUiState()
        observeItemCount()
        handleBackPress()
    }

    private fun observeItemCount() {
        productCount?.observe(this) {
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
                    }
                    is Resource.Failure -> {

                    }
                    else -> {}
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
    }

    override fun clickAddToCart() {
        Toast.makeText(this.requireContext(), "add item to Cart", Toast.LENGTH_SHORT).show()
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
