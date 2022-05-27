package com.czh.studyproject.http.exception

import com.czh.http.handler.ApiExceptionHandler

/**
 * @Description 用于处理一些比较特殊的Api错误
 * @Author CZH
 * @Date 2021/5/17 14:06
 */
object ApiExceptionHandlerImpl : ApiExceptionHandler {

    private const val TOKEN_INVALIDATION = 10001 //根据后端定义，这里随便编一个

    override fun handleException(errorCode: Int): Boolean {
        return when (errorCode) {
            TOKEN_INVALIDATION -> {
                // token失效处理
                true
            }

            else -> {
                // 无关紧要的就不处理了
                false
            }
        }
    }
}