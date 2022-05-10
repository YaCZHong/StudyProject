package com.czh.studyproject

import android.app.Application
import com.czh.crash.CrashConfig
import com.czh.crash.CrashHandler
import com.czh.http.HttpConfig
import com.czh.http.HttpManager
import com.czh.studyproject.http.ApiExceptionHandlerImpl
import com.czh.studyproject.http.TokenAuthenticatorImpl
import com.czh.xhlib.AppConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppConfig.init(this)

        HttpManager.init(
            HttpConfig.Builder()
                .setBaseUrl("")
                .setAuthenticator(TokenAuthenticatorImpl)
                .setApiExceptionHandler(ApiExceptionHandlerImpl)
                .build()
        )
        CrashHandler.init(this, CrashConfig.Builder().build())
    }
}