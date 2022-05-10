package com.czh.http.handler

interface ApiExceptionHandler {

    /**
     * @return true, 已处理; false, 未处理
     */
    fun handleException(errorCode: Int): Boolean
}