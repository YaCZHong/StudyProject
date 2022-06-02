package com.czh.http.response

import com.czh.http.HttpManager

sealed class ApiResult<out T> {
    companion object {
        fun <E> createApiResult(response: BaseResponse<E>): ApiResult<E> {
            return if (response.isSuccess()) {
                ApiSuccessResult(response.data)
            } else {
                ApiFailureResult(response.code, response.msg)
            }
        }

        fun createApiResult(e: Throwable): ApiErrorResult {
            val errorMsg = HttpManager.config.httpExceptionHandler.handleException(e)
            return ApiErrorResult(-1, errorMsg)
        }
    }
}

data class ApiSuccessResult<E>(val body: E) : ApiResult<E>()
data class ApiFailureResult(val apiCode: Int, val apiMsg: String) : ApiResult<Nothing>()
data class ApiErrorResult(val errorCode: Int, val errorMsg: String) : ApiResult<Nothing>()