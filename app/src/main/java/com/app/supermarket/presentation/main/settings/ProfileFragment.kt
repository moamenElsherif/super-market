package com.app.supermarket.presentation.main.settings

import android.os.Bundle
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentProfileBinding


class ProfileFragment :BaseFragment<FragmentProfileBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun initUI(savedInstanceState: Bundle?) {
    }
}