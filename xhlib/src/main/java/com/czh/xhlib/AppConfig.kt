package com.czh.xhlib

import android.app.Application
import android.content.Context

object AppConfig {

    private lateinit var application: Application
    val context: Context
        get() = application.applicationContext

    fun init(application: Application) {
        this.application = application
    }
}