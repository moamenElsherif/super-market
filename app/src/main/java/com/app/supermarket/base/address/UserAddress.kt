package com.app.supermarket.base.address

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.app.supermarket.base.AuthPreference
import com.app.supermarket.base.BasePreferenceStorage
import com.app.supermarket.presentation.address.UserAddressUiState
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

object UserAddress{

    var sharedPreference: SharedPreferences? = null
    private const val SHARED_PREF = "shared_pref"
    const val USER_ADDRESS = "user_address"


    fun init(context: Context) {
        sharedPreference = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
    }

    @SuppressLint("CommitPrefEdits")
    fun saveAddress(addressModel: UserAddressUiState){
        sharedPreference?.edit()?.apply { putString(USER_ADDRESS, addressModel.toString()) }
    }

    fun getUserAddress(): UserAddressUiState{
        return try {
            val userAddress = sharedPreference?.getString(USER_ADDRESS, null)
            Gson().fromJson(userAddress, UserAddressUiState::class.java)
        } catch (ex: Exception) {
            UserAddressUiState()
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun resetUserAddress() {
        sharedPreference?.edit()?.apply {putString(USER_ADDRESS, null)}
    }

}