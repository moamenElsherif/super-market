package com.app.supermarket.presentation.main.home.productlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentProductListBinding


class ProductListFragment : BaseFragment<FragmentProductListBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_product_list

    override fun initUI(savedInstanceState: Bundle?) {

    }

}