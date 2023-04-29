package com.app.supermarket.presentation.authentication.login

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Constants
import com.app.supermarket.base.Resource
import com.app.supermarket.databinding.FragmentLoginBinding
import com.app.supermarket.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), LoginListener {
    override val layoutRes: Int
        get() = R.layout.fragment_login

    private var phoneNumber: String? = null
    private var password: String? = null

    private val viewModel: LoginViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        binding.loginListener = this
        observeEvents()
        setupObservers()
        initLang()
    }

    private fun initLang() {
        if (getCurrentLanguage.language == Constants.english) binding.tvLang.text = Constants.arabic
        else binding.tvLang.text = Constants.english
    }

    private fun observeEvents() {
        viewModel.loginEvents.observe(this) {
            when (it) {
                LoginEvents.InvalidNumber -> {
                    createToast(R.string.invalid_number)
                }
                LoginEvents.PasswordEmpty -> {
                    createToast(R.string.empty_password)
                }
                LoginEvents.PhoneNumberEmpty -> {
                    createToast(R.string.empty_number)
                }
                LoginEvents.EmptyData -> {
                    createToast(R.string.empty_data)
                }
                else -> createToast(R.string.invalid_data)
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.logInResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        showLoading()
                        hideKeyboard()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        createToast(R.string.login)
                        restartApp()
                    }
                    is Resource.Failure -> {
                        hideLoading()
                        createToast(R.string.anErrorOccured)
                    }
                    else -> {
                        hideLoading()
                    }
                }
            }
        }
    }

    override fun clickLogin() {
        phoneNumber = binding.edPhone.text?.trim().toString()
        password = binding.edPassword.text?.trim().toString()
        viewModel.login(phoneNumber, password)
    }

    override fun clickRegister() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    override fun clickForgetPassword() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment())
    }

    override fun clickChangeLang() {
        if (getCurrentLanguage.language == Constants.english) setLocale("ar") else setLocale("en")
        this.requireActivity().recreate()
    }

    override fun loginAsGuest() {
        startActivity(Intent(this.requireContext(), MainActivity::class.java))
    }

    companion object {
        const val USER_LOGGED_IN_EVENT_TAG = "USER_LOGGED_IN_EVENT_TAG"
    }

}