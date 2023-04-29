package com.app.supermarket.base

import androidx.annotation.Keep

@Keep
data class BaseResponse<T>(
  val result: T,
  val targetUrl :String,
  val success: Boolean,
  val error: String?,
  val unAuthorizedRequest: Boolean,
  val __abp: Boolean
)