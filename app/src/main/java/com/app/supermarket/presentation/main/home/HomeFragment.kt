package com.app.supermarket.presentation.main.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.data.models.response.CategoryResponse
import com.app.supermarket.data.models.response.HomeCategoryResponse
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
                }
            }
        }

        initAdapter()
    }

    private fun initAdapter() {
        val itemList = mutableListOf<CategoryResponse>()
        val item = CategoryResponse(
            null,
            title = "FoodFoodFood",
            imageUrl = R.drawable.user.toString(),
            id = null,
            description = null,
            isActive = null,
            localizedDescription = null,
            localizedTitle = null
        )

        repeat(12) {
            itemList.add(it ,item)
        }
                    else -> {
                        hideLoading()
                    }
                }
            }
        }
    }

    private fun initAdapter(data: List<Item> ) {
        categoryAdapter = HomeCategoryAdapter(AdapterClickListener { category ->

        })

        categoryAdapter.submitList(itemList)

        binding.apply {
            rvCategory.layoutManager = GridLayoutManager(requireContext() ,2).apply {
                this.isSmoothScrolling
            }

            rvCategory.setHasFixedSize(false)
            rvCategory.adapter = categoryAdapter

        }

}