package com.app.supermarket.base

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.HashMap
import javax.inject.Inject
import kotlin.Exception

open class BaseRemoteDataSource @Inject constructor() {
    var gson: Gson = Gson()
    protected fun getParameters(requestData: Any): Map<String, String> {
        val params: MutableMap<String, String> = HashMap()
        try {
            val jsonObject = JSONObject(gson.toJson(requestData))
            for (i in 0 until jsonObject.names().length()) {
                params[jsonObject.names().getString(i)] = jsonObject[jsonObject.names().getString(i)].toString() + ""
            }
        } catch (e: Exception) {
            e.stackTrace
        }
        return params
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return try {
            val apiResponse = apiCall.invoke()
            Timber.d("=> ${(apiResponse as BaseResponse<*>).success} <==")
            println(apiResponse)
            return when (apiResponse.success) {
                true -> Resource.Success(apiResponse)
                else -> Resource.Failure(FailureStatus.API_FAIL)
            }
        }catch (e: Exception){
            Timber.d(e)
            Resource.Failure(FailureStatus.API_FAIL)
        }

    }
}