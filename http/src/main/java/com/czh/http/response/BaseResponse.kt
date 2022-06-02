package com.czh.http.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BaseResponse<T>(
    @SerializedName("errorCode") val code: Int,
    @SerializedName("errorMsg") val msg: String,
    val data: T
) {
    fun isSuccess() = code == 0
}