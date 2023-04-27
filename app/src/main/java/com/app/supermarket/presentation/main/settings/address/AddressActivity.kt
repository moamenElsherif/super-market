package com.app.supermarket.presentation.main.settings.address

import android.os.Bundle
import androidx.activity.viewModels
import com.app.supermarket.R
import com.app.supermarket.base.BaseActivity
import com.app.supermarket.databinding.ActivityAddressBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressActivity : BaseActivity<ActivityAddressBinding>() {

    val viewModel: AddressViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.activity_address

    override fun initUI(savedInstanceState: Bundle?) {
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.userAddress.observe(this) { userAddressUiState ->

        }
    }

    private fun initClickListeners() {
        binding.apply {
            toolBarAddress.setNavigationOnClickListener {
                popBackToMainActivity()
            }
        }
    }

    private fun popBackToMainActivity() {
        finish()
    }

}