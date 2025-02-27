package com.app.supermarket.presentation.product.productsList

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants.CATEGORY_ID
import com.app.supermarket.base.Constants.CATEGORY_NAME
import com.app.supermarket.base.Constants.SWIPE_REFRESH_TIME
import com.app.supermarket.base.Resource
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.databinding.FragmentProductListBinding
import com.app.supermarket.presentation.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
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
        initObservers()
        val extras = activity?.intent?.extras

        // if extras is null then return back to main activity
        if (extras == null) navigateToMainActivity()

        val categoryId = extras!!.getInt(CATEGORY_ID)
        categoryName = extras.getString(CATEGORY_NAME).toString()

        initSwipeRefreshListener(categoryId)
        initSearchInputObserver()

        getCategoryList(categoryId)

        binding.apply {
            toolBarProductList.apply {
                title = categoryName

                setNavigationOnClickListener {
                    requireActivity().finish()
                }
            }

            appBarProductList.setOnClickListener {
                val dialog = BottomSheetDialog(requireContext())

                val view = layoutInflater.inflate(R.layout.fragment_product_list, null)

                dialog.setCancelable(true)

                dialog.setContentView(view)

                dialog.show()
            }
        }
    }

    private fun getCategoryList(categoryId: Int) {
        viewModel.getAllCategoryProductsById(categoryId)
        observeResponse()
    }

    private fun initSwipeRefreshListener(categoryId: Int) {
        binding.apply {
            swipeRefreshLayoutProducts.setOnRefreshListener {
                viewModel.getAllCategoryProductsById(categoryId)
                Handler(Looper.myLooper()!!).postDelayed({
                    swipeRefreshLayoutProducts.isRefreshing = false
                }, SWIPE_REFRESH_TIME)
            }
        }
    }

    private fun initObservers() {
        viewModel.productsSearchResultsLiveData.observe(viewLifecycleOwner) { products ->
            productListAdapter.submitList(products)
        }
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

    private fun initSearchInputObserver() {
        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchProducts(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
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

            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                this.isSmoothScrolling
            }

            setHasFixedSize(false)
            adapter = productListAdapter
        }
    }
}