package com.czh.http

import com.czh.http.HttpManager.init
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * 使用该单例时，需要在Application中先对该单例进行初始化
 * @see init
 */
object HttpManager {

    lateinit var config: HttpConfig

    fun init(config: HttpConfig) {
        this.config = config
    }

    /**
     * okHttpClient变量，可供应用内全局使用
     */
    val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(config.timeout, TimeUnit.SECONDS)
            .readTimeout(config.timeout, TimeUnit.SECONDS)
            .writeTimeout(config.timeout, TimeUnit.SECONDS)
            .cache(Cache(File(config.cacheDir), config.maxCacheSize))
        config.interceptors.forEach { builder.addInterceptor(it) }
        builder.build()
    }

    /**
     * retrofit变量，可供应用内全局使用，不过要注意baseUrl已经固定
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}