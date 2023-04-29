package com.app.supermarket.presentation.address

import android.os.Bundle
import androidx.activity.viewModels
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivityAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : BaseActivity<ActivityAddressBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_address

    override fun initUI(savedInstanceState: Bundle?) {
    }
}