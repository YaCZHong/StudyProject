package com.czh.http.handler

interface HttpExceptionHandler {

    fun handleException(e: Throwable): String
}