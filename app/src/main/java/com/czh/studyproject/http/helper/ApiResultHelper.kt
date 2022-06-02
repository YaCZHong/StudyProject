package com.czh.studyproject.http.helper

import com.czh.http.response.ApiErrorResult
import com.czh.http.response.ApiFailureResult
import com.czh.http.response.ApiResult
import com.czh.http.response.ApiSuccessResult
import com.czh.studyproject.http.ApiExceptionHandlerImpl
import com.czh.xhlib.toast.toast

/**
 * 网络请求结果处理辅助类。
 * 这里会加上一些默认的处理，如需自定义处理，就传入相应的回调代码块。
 * (注!!!)在接口返回失败（ApiFailureResult）的时候，会优先使用 ApiExceptionHandlerImpl 进行处理，ApiExceptionHandlerImpl 不处理的，才会回调 onFailure 方法。
 */
fun <T> handleApiResult(
    apiResult: ApiResult<T>,
    onSuccess: (T) -> Unit,
    onFailure: ((apiCode: Int, apiMsg: String) -> Unit)? = null,
    onError: ((errorCode: Int, errorMsg: String) -> Unit)? = null
) {
    when (apiResult) {
        is ApiSuccessResult -> {
            onSuccess.invoke(apiResult.body)
        }
        is ApiFailureResult -> {
            // 特殊异常码处理优先级高，非特殊异常码才会回调自定义处理方法
            val processed = ApiExceptionHandlerImpl.handleException(apiResult.apiCode)
            if (processed) {
                return
            }
            onFailure?.invoke(apiResult.apiCode, apiResult.apiMsg)
        }
        is ApiErrorResult -> {
            onError?.invoke(apiResult.errorCode, apiResult.errorMsg) ?: toast(apiResult.errorMsg)
        }
    }
}