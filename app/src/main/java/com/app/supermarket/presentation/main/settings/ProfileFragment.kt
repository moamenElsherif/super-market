package com.app.supermarket.presentation.main.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.auth.Auth
import com.app.supermarket.databinding.FragmentProfileBinding
import com.app.supermarket.presentation.authentication.AuthenticationActivity
import com.app.supermarket.presentation.address.AddressActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment :BaseFragment<FragmentProfileBinding>(), ProfileClickListener {

    @Inject lateinit var auth: Auth

    private val viewModel: ProfileViewModel by viewModels()

    override val layoutRes: Int
        get() = R.layout.fragment_profile

    override fun initUI(savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = this@ProfileFragment
            fragmentListener = this@ProfileFragment
        }
        checkAuthenticationState()
        initObservers()
    }

    private fun initObservers() {
        viewModel.userDataUiState.observe(viewLifecycleOwner) { uiState ->
            binding.uiState = uiState
        }
    }

    private fun checkAuthenticationState() {
        binding.apply {
            if (auth.isLoggedIn()) {
                btnLogin.visibility = View.GONE
                tvLoginMessage.visibility = View.GONE
                // data
                circleImageView.visibility = View.VISIBLE
                btnEditAddress.visibility = View.VISIBLE
                ivUserImage.visibility = View.VISIBLE
                tvUserName.visibility = View.VISIBLE
                tvUserEmail.visibility = View.VISIBLE
                btnLogout.visibility = View.VISIBLE
            } else {
                btnLogin.visibility = View.VISIBLE
                tvLoginMessage.visibility = View.VISIBLE
                // data
                circleImageView.visibility = View.GONE
                btnEditAddress.visibility = View.GONE
                ivUserImage.visibility = View.GONE
                tvUserName.visibility = View.GONE
                tvUserEmail.visibility = View.GONE
                btnLogout.visibility = View.GONE
            }
        }
    }

    override fun onLogoutClickListener() {
        auth.logout()
        navigateToAuthActivity(closeCurrent = true)
    }

    override fun onLoginClickListener() {
        navigateToAuthActivity()
    }

    override fun onEditAddressClickListener() {
        navigateToAddressActivity()
    }

    private fun navigateToAddressActivity() {
        val intent = Intent(requireActivity(), AddressActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAuthActivity(closeCurrent: Boolean = false) {
        val intent = Intent(requireActivity(), AuthenticationActivity::class.java)
        startActivity(intent)
        if (closeCurrent) requireActivity().finish()
    }
}