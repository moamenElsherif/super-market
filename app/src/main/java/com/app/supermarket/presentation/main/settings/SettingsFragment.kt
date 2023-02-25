package com.app.supermarket.presentation.main.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentSettingsBinding


class SettingsFragment :BaseFragment<FragmentSettingsBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_settings

    override fun initUI(savedInstanceState: Bundle?) {
    }
}