package com.app.supermarket.presentation.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(){
    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun initUI(savedInstanceState: Bundle?) {
    }
}