package com.czh.http.response

import com.czh.http.HttpManager

sealed class ApiResponse<T : BaseResponse<*>> {
    class ApiSuccessResponse<T : BaseResponse<*>>(val body: T) : ApiResponse<T>()
    class ApiFailureResponse<T : BaseResponse<*>>(val apiCode: Int, val apiMsg: String) :
        ApiResponse<T>()

    class ApiErrorResponse<T : BaseResponse<*>>(val errorCode: Int, val errorMsg: String) :
        ApiResponse<T>()

    companion object {
        fun <T : BaseResponse<*>> create(response: T): ApiResponse<T> {
            if (response.isSuccess()) {
                return ApiSuccessResponse(response)
            }
            return ApiFailureResponse(response.code, response.msg)
        }

        fun <T : BaseResponse<*>> create(e: Throwable): ApiResponse<T> {
            val errorMsg = HttpManager.config.httpExceptionHandler.handleException(e)
            return ApiErrorResponse(-1, errorMsg)
        }
    }
}