package com.czh.http

import android.util.Log
import com.czh.http.interceptor.HttpResponseInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 使用该单例时，需要在Application中先对该单例进行初始化
 * @see init
 */
object HttpManager {

    private var mBaseUrl: String? = null
    var mErrorCallBack: ((errorCode: Int) -> Unit)? = null

    /**
     * okHttpClient变量，可供应用内全局使用
     */
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
//            .addInterceptor(logger)
//            .addInterceptor(HttpHeaderInterceptor())
            .addInterceptor(HttpResponseInterceptor())
            .build()
    }

    /**
     * retrofit变量，可供应用内全局使用，不过要注意baseUrl已经固定
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 网络日志拦截，打印相关网络请求的相应数据
     */
    private val logger: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor { message -> Log.e("HTTP_LOG", message) }.also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * 初始化HttpManager
     * @param baseUrl retrofit所需的baseUrl
     * @param errorCallback 接口状态码错误的处理回调方法
     */
    fun init(baseUrl: String, errorCallback: (errorCode: Int) -> Unit) {
        this.mBaseUrl = baseUrl
        this.mErrorCallBack = errorCallback
    }
}