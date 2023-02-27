package com.app.supermarket.presentation.main.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.base.callback.AdapterClickListener
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

        binding.apply {
            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

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