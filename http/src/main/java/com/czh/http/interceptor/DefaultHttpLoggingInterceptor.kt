package com.czh.http.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 网络日志拦截，打印相关网络请求的相应数据
 */

val defaultHttpLoggingInterceptor: HttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor { message -> Log.e("HTTP_LOG", message) }.also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
}