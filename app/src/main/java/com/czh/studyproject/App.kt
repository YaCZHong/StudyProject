package com.czh.studyproject

import android.app.Application
import com.czh.crash.CrashConfig
import com.czh.crash.CrashHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CrashHandler.init(this, CrashConfig.Builder().build())
    }
}