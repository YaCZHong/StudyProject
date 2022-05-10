package com.czh.http

import androidx.annotation.Keep

/**
 * 可用于做日志上报
 */
@Keep
data class HttpReportBean(
    val requestMethod: String,
    val requestUrl: String,
    val responseCode: String,
    val responseBodyStr: String,
    val tookMs: String
)