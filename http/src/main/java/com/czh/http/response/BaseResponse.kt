package com.czh.http.response

import android.os.Parcelable

abstract class BaseResponse<T>(
    val code: Int = 0,
    val msg: String = "",
    val data: T? = null
) : Parcelable {
    fun isSuccess() = code == 0
}