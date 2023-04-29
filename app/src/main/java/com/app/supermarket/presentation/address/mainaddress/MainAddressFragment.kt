package com.app.supermarket.presentation.address.mainaddress

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.supermarket.R
import com.app.supermarket.base.BaseFragment
import com.app.supermarket.base.Resource
import com.app.supermarket.databinding.FragmentMainAddressBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainAddressFragment : BaseFragment<FragmentMainAddressBinding>(), MainAddressListener {
    override val layoutRes: Int
        get() = R.layout.fragment_main_address

    private var editMode = false

    private val viewModel: MainAddressViewModel by viewModels()

    override fun initUI(savedInstanceState: Bundle?) {
        binding.listener = this
        observeUserAddress()
        observeUserAddressChange()
    }

    private fun observeUserAddressChange() {
        viewModel.modifiedAddress.observe(this){
            binding.address = it
        }
    }

    private fun observeUserAddress() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.addressResponse.collectLatest {
                when(it){
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        viewModel.modifiedAddress.value = it.value.result?.toUserAddressUiState()
                    }
                    else-> {
                        hideLoading()
                    }
                }
            }
        }
    }

    override fun clickSaveEdit() {
        if (checkDataValid()){
            handleEditMode(editMode)
            updateAddress(editMode)
            editMode = !editMode
        }
        else {
            createToast(R.string.invalid_data)
        }
    }

    private fun updateAddress(editMode: Boolean) {
        if (!editMode) {
            viewModel.updateAddress()
        }
    }

    private fun checkDataValid(): Boolean {
        if (binding.edStreet.text.isNullOrEmpty()) {
            return false
        }
        if (binding.edApartmentNum.text.isNullOrEmpty()) {
            return false
        }
        if (binding.edBuildingNum.text.isNullOrEmpty()) {
            return false
        }
        if (binding.edAddressNote.text.isNullOrEmpty()) {
            return false
        }
        return true
    }

    private fun handleEditMode(editMode: Boolean) {
        if (editMode) {
            binding.tvEdit.text = getString(R.string.save)
            binding.tvEdit.setTextColor(resources.getColor(R.color.green))
        } else {
            binding.tvEdit.text = getString(R.string.edit)
            binding.tvEdit.setTextColor(resources.getColor(R.color.black))
        }
        binding.edApartmentNum.isEnabled = editMode
        binding.edBuildingNum.isEnabled = editMode
        binding.edStreet.isEnabled = editMode
        binding.edAddressNote.isEnabled = editMode
    }

    override fun clickPickAnotherAddress() {
        TODO("Not yet implemented")
    }

    override fun clickBack() {
        TODO("Not yet implemented")
    }

}