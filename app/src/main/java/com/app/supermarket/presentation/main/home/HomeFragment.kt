package com.app.supermarket.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants.SWIPE_REFRESH_TIME
import com.app.supermarket.base.Constants.CATEGORY_NAME
import com.app.supermarket.base.Constants.CATEGORY_ID
import com.app.supermarket.base.Resource
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.databinding.FragmentHomeBinding
import com.app.supermarket.presentation.product.ProductActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home


    private val viewModel: HomeViewModel by viewModels()

    private var categoryAdapter: HomeCategoryAdapter = HomeCategoryAdapter(AdapterClickListener { item ->
        navigateToProductActivity(item.id , item.localizedTitle)
    })

    override fun initUI(savedInstanceState: Bundle?) {
        initAdapter()
        initSwipeRefresherListener()
        binding.apply {
            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    viewModel.searchCategories(s.toString())
                }
            })
        }

        lifecycleScope.launchWhenResumed {
            viewModel.categoryStateFlow.collect { resource ->
                when (resource) {
                    is Resource.Failure -> {
                        hideLoading()
                        Toast.makeText(
                            this@HomeFragment.requireContext(),
                            resource.failureStatus.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        categoryAdapter.submitList(resource.value.result.items)
                    }
                    else -> {}
                }
            }
        }

        viewModel.categoriesSearchResultsLiveData.observe(viewLifecycleOwner) { searchResult ->
            categoryAdapter.submitList(searchResult)
        }
    }

    private fun navigateToProductActivity(categoryId: Int , categoryName: String) {
        val intent = Intent(requireContext(), ProductActivity::class.java)
        intent.putExtra(CATEGORY_ID, categoryId)
        intent.putExtra(CATEGORY_NAME , categoryName)
        startActivity(intent)
    }

    private fun initSwipeRefresherListener() {
        binding.apply {
            swipeRefreshLayoutCategories.setOnRefreshListener {
                viewModel.getAllCategory()
                Handler(Looper.myLooper()!!).postDelayed({
                    swipeRefreshLayoutCategories.isRefreshing = false
                }, SWIPE_REFRESH_TIME)
            }
        }
    }
    private fun initAdapter() {
        binding.rvCategory.apply {

            val width = resources.displayMetrics.run { widthPixels }
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                this.isSmoothScrolling
            }

            categoryAdapter.setItemWidth(width)

            setHasFixedSize(false)
            adapter = categoryAdapter
        }
    }

}