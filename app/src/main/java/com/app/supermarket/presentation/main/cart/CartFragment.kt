package com.app.supermarket.presentation.main.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentCartBinding

class CartFragment :BaseFragment<FragmentCartBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_cart

    override fun initUI(savedInstanceState: Bundle?) {
    }
}