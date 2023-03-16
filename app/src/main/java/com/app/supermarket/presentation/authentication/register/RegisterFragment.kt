package com.app.supermarket.presentation.authentication.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.databinding.FragmentRegisterBinding
import com.app.supermarket.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>() ,RegisterListener {
    override val layoutRes: Int
        get() = R.layout.fragment_register

    private val viewModel: RegisterViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        binding.listener = this
        observeEvents()
        setupObservers()
    }

    private fun observeEvents() {
        viewModel.registerEvents.observe(this){
            when(it){
                RegisterEvents.InvalidPassword -> createToast(R.string.empty_password)
                RegisterEvents.EmptyEmail -> createToast(R.string.empty_email)
                RegisterEvents.InvalidName -> createToast(R.string.empty_name)
                RegisterEvents.InvalidEmail -> createToast(R.string.invalid_email)
            }
        }
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenResumed {
            viewModel.registerResponse.collect {
                when (it) {
                    Resource.Loading -> {
                        showLoading()
                        hideKeyboard()
                    }
                    is Resource.Success -> {
                        hideLoading()
                        if (it.value.result.canLogin) openMainActivity()
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

    private fun openMainActivity() {
        val intent = Intent(this.requireContext() , MainActivity::class.java)
        startActivity(intent)
    }

    override fun clickContinue() {
        val name = binding.edName.text?.trim().toString()
        val email = binding.edEmail.text?.trim().toString()
        val password = binding.edPassword.text?.trim().toString()
        viewModel.registerUser(name , email , password)
    }

}