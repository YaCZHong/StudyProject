package com.czh.http.handler.impl

import com.czh.http.R
import com.czh.http.handler.HttpExceptionHandler
import com.czh.xhlib.AppConfig
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

object DefaultHttpExceptionHandlerImpl : HttpExceptionHandler {

    override fun handleException(e: Throwable): String {
        return if (e is SocketTimeoutException || e is TimeoutException) {
            AppConfig.context.resources.getString(R.string.http_timeout_error)
        } else if (e is ConnectException || e is HttpException || e is UnknownHostException) {
            AppConfig.context.resources.getString(R.string.http_connect_error)
        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
            AppConfig.context.resources.getString(R.string.http_parse_error)
        } else if (e is SSLHandshakeException) {
            AppConfig.context.resources.getString(R.string.http_certificate_error)
        } else if (e is IllegalArgumentException) {
            AppConfig.context.resources.getString(R.string.http_param_error)
        } else {
            e.message.toString()
        }
    }
}