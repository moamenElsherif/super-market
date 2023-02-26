package com.app.supermarket.presentation.main.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryResponse
import com.app.supermarket.data.models.response.HomeCategoryResponse
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding>(){
    override val layoutRes: Int
        get() = R.layout.fragment_home


    private val viewModel : HomeViewModel by viewModels()

    private lateinit var categoryAdapter: HomeCategoryAdapter

    override fun initUI(savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenResumed {
            viewModel.categoryStateFlow.collect { resource ->
                when(resource) {
                    is Resource.Failure -> {
                        hideLoading()
                        Toast.makeText(this@HomeFragment.requireContext(), resource.failureStatus.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        initAdapter(resource.value.result.items)
                    }
                    else -> {
                        hideLoading()
                    }
                }
            }
        }
    }

    private fun initAdapter(data: List<Item> ) {
        categoryAdapter = HomeCategoryAdapter(data)
        binding.rvCategory.layoutManager = GridLayoutManager(this.requireContext() ,2).apply {
            this.isSmoothScrolling
        }
        binding.rvCategory.setHasFixedSize(false)
        binding.rvCategory.adapter = categoryAdapter
    }

}