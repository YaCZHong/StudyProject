package com.czh.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        newRequestBuilder.addHeader("A", "AAA")
        newRequestBuilder.addHeader("B", "BBB")
        return chain.proceed(newRequestBuilder.build())
    }
}