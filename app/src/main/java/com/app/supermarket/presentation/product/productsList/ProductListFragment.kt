package com.app.supermarket.presentation.product.productsList

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants
import com.app.supermarket.base.Resource
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.databinding.FragmentProductListBinding
import com.app.supermarket.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_product_list

    private var categoryName: String = ""

    private val productListAdapter: ProductListAdapter =
        ProductListAdapter(AdapterClickListener { product ->
            findNavController().navigate(
                ProductListFragmentDirections.actionProductListFragmentToProductDetailsFragment(
                    product.id!! , categoryName
                )
            )
        })

    private val viewModel: ProductListViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        initAdapter()
        val extras = activity?.intent?.extras

        // if extras is null then return back to main activity
        if (extras == null) navigateToMainActivity()

        val categoryId = extras!!.getInt(Constants.CATEGORY_ID)
        categoryName = extras.getString(Constants.CATEGORY_NAME).toString()

        binding.tvTitle.text = categoryName
        getCategoryList(categoryId)

    }

    private fun getCategoryList(categoryId: Int) {
        viewModel.getAllCategoryProductsById(categoryId)
        observeResponse()
    }

    private fun observeResponse() {
        lifecycleScope.launchWhenResumed {
            viewModel.productsStateFlow.collect { resource ->
                when (resource) {
                    is Resource.Failure -> {
                        hideLoading()
                        Toast.makeText(
                            this@ProductListFragment.requireContext(),
                            resource.failureStatus.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        checkEmptyList(resource.value.result.items?.size)
                        productListAdapter.submitList(resource.value.result.items)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun checkEmptyList(size: Int?) {
        if (size == 0) {
            binding.rvProducts.visibility = View.GONE
            binding.tvEmptyList.visibility = View.VISIBLE
        } else {
            binding.rvProducts.visibility = View.VISIBLE
            binding.tvEmptyList.visibility = View.GONE
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun initAdapter() {
        binding.rvProducts.apply {

            // get screen width
            val width = resources.displayMetrics.run { widthPixels }

            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                this.isSmoothScrolling
            }

            productListAdapter.setItemWidth(width)

            setHasFixedSize(false)
            adapter = productListAdapter
        }
    }
}