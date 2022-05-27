package com.czh.http.interceptor

import android.util.Log
import com.czh.http.HttpManager
import com.czh.http.HttpReportBean
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.promisesBody
import okio.Buffer
import okio.GzipSource
import org.json.JSONObject
import java.io.EOFException
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

class DefaultHttpResponseInterceptor : Interceptor {

    companion object {
        private const val TAG = "HttpResponseInterceptor"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestMethod: String
        val requestUrl: String
        val responseCode: String
        val responseBodyStr: String
        val takeMs: String

        val request = chain.request()
        requestMethod = request.method
        requestUrl = "${request.url}"

        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            responseCode = ""
            responseBodyStr = "HTTP FAILED: $e"
            takeMs = ""
            val httpReportBean =
                HttpReportBean(requestMethod, requestUrl, responseCode, responseBodyStr, takeMs)
//            Log.e(TAG, httpReportBean.toString())
            throw e
        }
        takeMs = "${TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)}ms"
        responseCode = "${response.code}"

        val responseBody = response.body!!
        val contentLength = responseBody.contentLength()
        val headers = response.headers
        if (!response.promisesBody()) {
            responseBodyStr = "No body"
        } else if (bodyHasUnknownEncoding(response.headers)) {
            responseBodyStr = "Encoded body omitted"
        } else {
            val source = responseBody.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.
            var buffer = source.buffer

            if ("gzip".equals(headers["Content-Encoding"], ignoreCase = true)) {
                GzipSource(buffer.clone()).use { gzippedResponseBody ->
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                }
            }

            val contentType = responseBody.contentType()
            val charset: Charset =
                contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8

            responseBodyStr = if (!buffer.isProbablyUtf8()) {
                "Binary ${buffer.size}-byte body omitted"
            } else if (contentLength != 0L) {
                buffer.clone().readString(charset)
            } else {
                "No body"
            }
        }

        val httpReportBean = HttpReportBean(requestMethod, requestUrl, responseCode, responseBodyStr, takeMs)
//        Log.e(TAG, httpReportBean.toString())
//        handleExceptionCode(responseBodyStr)

        return response
    }

    private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"] ?: return false
        return !contentEncoding.equals("identity", ignoreCase = true) &&
                !contentEncoding.equals("gzip", ignoreCase = true)
    }

    private fun Buffer.isProbablyUtf8(): Boolean {
        try {
            val prefix = Buffer()
            val byteCount = size.coerceAtMost(64)
            copyTo(prefix, 0, byteCount)
            for (i in 0 until 16) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            return true
        } catch (_: EOFException) {
            return false // Truncated UTF-8 sequence.
        }
    }

//    private fun handleExceptionCode(responseBodyStr: String) {
//        try {
//            val bodyObject = JSONObject(responseBodyStr)
//            val code = bodyObject.optInt("code")
//            when (code) {
//                0 -> {
//                    Log.e(TAG, "CODE IS NO PROBLEM")
//                }
//                else -> {
//                    Log.e(TAG, "CODE ERROR")
//                    HttpManager.config.apiExceptionHandler?.handleException(code)
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
}