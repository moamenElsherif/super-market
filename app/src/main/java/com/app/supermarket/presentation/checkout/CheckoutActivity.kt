package com.app.supermarket.presentation.checkout

import android.os.Bundle
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivityCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutActivity() : BaseActivity<ActivityCheckoutBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_checkout


    override fun initUI(savedInstanceState: Bundle?) {

    }

}