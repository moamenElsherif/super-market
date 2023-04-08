package com.app.supermarket.presentation.main.settings

import android.os.Bundle
import android.view.View
import com.app.supermarket.R
import com.app.supermarket.base.AuthPreference
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment :BaseFragment<FragmentProfileBinding>() {

    @Inject lateinit var authPreference: Auth

    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun initUI(savedInstanceState: Bundle?) {
        checkAuthenticationState()
    }

    private fun checkAuthenticationState() {
        binding.apply {
            if (authPreference.isLoggedIn()) {
                btnLogin.visibility = View.GONE
                tvLoginMessage.visibility = View.GONE
                // data
                circleImageView.visibility = View.VISIBLE
                ivUserImage.visibility = View.VISIBLE
                tvUserName.visibility = View.VISIBLE
                tvUserEmail.visibility = View.VISIBLE
                btnLogout.visibility = View.VISIBLE
            } else {
                btnLogin.visibility = View.VISIBLE
                tvLoginMessage.visibility = View.VISIBLE
                // data
                circleImageView.visibility = View.GONE
                ivUserImage.visibility = View.GONE
                tvUserName.visibility = View.GONE
                tvUserEmail.visibility = View.GONE
                btnLogout.visibility = View.GONE
            }
        }
    }
}