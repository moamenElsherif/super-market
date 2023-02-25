package com.app.supermarket.presentation.authentication.forgetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentForgetPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_forget_password

    private val viewModel: ForgetPasswordViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        observeEvents()
        clickConfirm()
    }

    private fun clickConfirm() {
        binding.cardView.setOnClickListener{
            viewModel.validatePasswords(binding.edPassword.text?.trim().toString() , binding.edConfirmPassword.text?.trim().toString() )
        }
    }

    private fun observeEvents() {
        viewModel.forgetPasswordEvent.observe(this){
            when(it){
                ForgetPasswordEvent.EmptyPassword -> createToast(R.string.empty_password)
                ForgetPasswordEvent.NotMatchPassword -> createToast(R.string.match_password_erroe)
            }
        }
    }
}