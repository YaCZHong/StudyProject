package com.czh.http

import com.czh.http.HttpConstants.DEFAULT_TIMEOUT
import com.czh.http.HttpConstants.MAX_CACHE_SIZE
import com.czh.http.handler.ApiExceptionHandler
import com.czh.http.handler.HttpExceptionHandler
import com.czh.http.interceptor.DefaultHttpHeaderInterceptor
import com.czh.http.interceptor.DefaultHttpResponseInterceptor
import com.czh.http.interceptor.defaultHttpLoggingInterceptor
import com.czh.xhlib.AppEnvironment
import okhttp3.Authenticator
import okhttp3.Interceptor
import com.czh.http.handler.impl.DefaultHttpExceptionHandlerImpl

class HttpConfig private constructor(
    val baseUrl: String,
    val timeout: Long,
    val cacheDir: String,
    val maxCacheSize: Long,
    val authenticator: Authenticator?,
    val interceptors: List<Interceptor>,
    val httpExceptionHandler: HttpExceptionHandler,
    val apiExceptionHandler: ApiExceptionHandler?
) {
    class Builder {
        private var baseUrl: String = ""
        private var timeout: Long = DEFAULT_TIMEOUT
        private var cacheDir: String = AppEnvironment.httpCacheDir
        private var maxCacheSize: Long = MAX_CACHE_SIZE
        private var authenticator: Authenticator? = null
        private var interceptors: List<Interceptor> = listOf(
            DefaultHttpHeaderInterceptor(),
            DefaultHttpResponseInterceptor(),
            defaultHttpLoggingInterceptor
        )
        private var httpExceptionHandler: HttpExceptionHandler = DefaultHttpExceptionHandlerImpl
        private var apiExceptionHandler: ApiExceptionHandler? = null

        fun setBaseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun setTimeout(timeout: Long): Builder {
            this.timeout = timeout
            return this
        }

        fun setCacheDir(cacheDir: String): Builder {
            this.cacheDir = cacheDir
            return this
        }

        fun setMaxCacheSize(maxCacheSize: Long): Builder {
            this.maxCacheSize = maxCacheSize
            return this
        }

        fun setAuthenticator(authenticator: Authenticator): Builder {
            this.authenticator = authenticator
            return this
        }

        fun setInterceptors(interceptors: List<Interceptor>): Builder {
            this.interceptors = interceptors
            return this
        }

        fun setHttpExceptionHandler(httpExceptionHandler: HttpExceptionHandler): Builder {
            this.httpExceptionHandler = httpExceptionHandler
            return this
        }

        fun setApiExceptionHandler(apiExceptionHandler: ApiExceptionHandler): Builder {
            this.apiExceptionHandler = apiExceptionHandler
            return this
        }

        fun build(): HttpConfig {
            return HttpConfig(
                baseUrl,
                timeout,
                cacheDir,
                maxCacheSize,
                authenticator,
                interceptors,
                httpExceptionHandler,
                apiExceptionHandler
            )
        }
    }
}