package com.app.supermarket.presentation.main.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.base.callback.AdapterClickListener
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home


    private val viewModel: HomeViewModel by viewModels()

    private var categoryAdapter: HomeCategoryAdapter = HomeCategoryAdapter(AdapterClickListener {

    })

    override fun initUI(savedInstanceState: Bundle?) {
        initAdapter()
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

    }

    private fun initAdapter() {
        binding.apply {
            rvCategory.layoutManager = GridLayoutManager(requireContext(), 2).apply {
                this.isSmoothScrolling
            }

            rvCategory.setHasFixedSize(false)
            rvCategory.adapter = categoryAdapter
        }

    }
}