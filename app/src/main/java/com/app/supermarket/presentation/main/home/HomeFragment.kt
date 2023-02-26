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
            viewModel.categoriesLiveData.collect { resource ->
                when(resource) {
                    is Resource.Default -> TODO()
                    is Resource.Failure -> Timber.d("Fail -> ${resource.failureStatus} ${resource.code}")
                    is Resource.Loading -> Timber.d("Loading..")
                    is Resource.Success -> {
                        val data = resource.value.data.items
                        Timber.d("Success $data")
                        Toast.makeText(requireActivity(), "Success ${data.size}", Toast.LENGTH_SHORT).show()
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

        repeat(12){
            itemList.add(it ,item)
        }

        val itemsTest = HomeCategoryResponse(items = itemList , totalCount = 5)

        categoryAdapter = HomeCategoryAdapter(itemsTest)
        binding.rvCategory.layoutManager = GridLayoutManager(this.requireContext() ,2).apply {
            this.isSmoothScrolling

        }
        binding.rvCategory.setHasFixedSize(false)
        binding.rvCategory.adapter = categoryAdapter
    }

}