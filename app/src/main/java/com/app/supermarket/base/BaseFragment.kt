package com.app.supermarket.base

import android.app.Dialog
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.supermarket.presentation.hideLoadingDialog
import com.app.supermarket.presentation.main.MainActivity
import com.app.supermarket.presentation.openActivityAndClearStack
import com.app.supermarket.presentation.showLoadingDialog
import java.lang.Exception
import java.util.*

abstract class BaseFragment<D : ViewDataBinding> : Fragment() {

    var isShown: Boolean = false
    private var progressDialog: Dialog? = null


    protected lateinit var binding: D


    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        initUI(savedInstanceState)
        binding.executePendingBindings()
        return binding.root
    }

    protected fun inflateLayout(layout: Int): View? {
        return layoutInflater.inflate(
            layout,
            null,
            false
        )
    }

    val getCurrentLanguage: Locale
        get() = Locale.getDefault()

    fun setLocale(lang: String?) {
        val locale = lang?.let { Locale(it) }
        if (locale != null) {
            Locale.setDefault(locale)
        }
        val config = Configuration()
        config.locale = locale
        context?.resources?.updateConfiguration(
            config,
            context?.resources?.displayMetrics
        )
    }

    fun showLoading() {
        hideLoading()
        progressDialog = showLoadingDialog(requireActivity(), null)
    }

    fun hideLoading() = hideLoadingDialog(progressDialog, requireActivity())

    fun restartApp(){
        this.requireActivity().openActivityAndClearStack(MainActivity::class.java)
        activity?.finish()
    }

    protected abstract fun initUI(savedInstanceState: Bundle?)

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView,
        adapter: RecyclerView.Adapter<VH>
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isHasFixedSize: Boolean,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(isHasFixedSize)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    fun <VH : RecyclerView.ViewHolder> setUpRcv(
        rcv: RecyclerView, adapter:
        RecyclerView.Adapter<VH>,
        isNestedScrollingEnabled: Boolean
    ) {
        rcv.setHasFixedSize(true)
        rcv.layoutManager = LinearLayoutManager(context)
        rcv.adapter = adapter
        rcv.isNestedScrollingEnabled = isNestedScrollingEnabled
    }

    open fun addFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if (activity is BaseActivity<*>)
            activity.addFragment(fragment, id, addToBackStack)
    }

    open fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if (activity is BaseActivity<*>)
            activity.addFragment(fragmentManager, fragment, id, addToBackStack)
    }

   open fun createToast(stringId: Int){
        Toast.makeText(this.requireContext(), this.requireContext().getString(stringId), Toast.LENGTH_SHORT).show()
    }


    open fun replaceFragment(
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {
        try {
            val activity = activity
            if (activity is BaseActivity<*>)
                activity.replaceFragment(fragment, id, addToBackStack)
        } catch (e: Exception) {
        }
    }

    open fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        id: Int,
        addToBackStack: Boolean
    ) {

        val activity = activity
        if (activity is BaseActivity<*>)
            activity.replaceFragment(fragmentManager, fragment, id, addToBackStack)
    }

    fun hideKeyboard() {
        activity?.let {
            if (it is BaseActivity<*>) {
                it.hideKeyboard()
            }
        }
    }

}