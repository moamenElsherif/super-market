package com.app.supermarket.presentation.product

import android.os.Bundle
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivityProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : BaseActivity<ActivityProductBinding>() {
    override val layoutRes: Int
        get() = R.layout.activity_product

    override fun initUI(savedInstanceState: Bundle?) {

    }
}