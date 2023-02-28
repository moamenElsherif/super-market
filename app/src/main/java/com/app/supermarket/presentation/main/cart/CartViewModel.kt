package com.app.supermarket.presentation.main.cart

import androidx.lifecycle.ViewModel
import com.app.supermarket.domain.usecase.ListAllUserCartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val listAllUserCartItemsUseCase: ListAllUserCartItemsUseCase
) : ViewModel() {

}