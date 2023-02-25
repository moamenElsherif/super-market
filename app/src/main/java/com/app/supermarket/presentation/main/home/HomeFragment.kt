package com.app.supermarket.presentation.main.home

import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.data.models.response.HomeCategoryResponse
import com.app.supermarket.data.models.response.Item
import com.app.supermarket.databinding.FragmentHomeBinding
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class HomeFragment :BaseFragment<FragmentHomeBinding>(){
    override val layoutRes: Int
        get() = R.layout.fragment_home

    lateinit var categoryAdapter: HomeCategoryAdapter

    override fun initUI(savedInstanceState: Bundle?) {
        initAdapter()
    }

    private fun initAdapter() {
        val itemList = mutableListOf<Item>()
        val item = Item(
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