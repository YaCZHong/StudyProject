package com.czh.studyproject

import android.app.Application
import com.czh.crash.CrashConfig
import com.czh.crash.CrashHandler

class App : Application() {
    companion object {
        private var instance: App? = null
        val sInstance: App
            get() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CrashHandler.init(this, CrashConfig.Builder().build())
    }
}