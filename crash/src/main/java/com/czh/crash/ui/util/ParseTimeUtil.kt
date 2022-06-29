package com.czh.crash.ui.util

import java.text.SimpleDateFormat
import java.util.*

internal object ParseTimeUtil {
    private const val FORMAT_PATTERN = "yyyy/MM/dd HH:mm"
    private val threadLocal = ThreadLocal<SimpleDateFormat>()

    private fun getSimpleDateFormat(): SimpleDateFormat {
        return threadLocal.get() ?: synchronized(this) {
            threadLocal.get() ?: SimpleDateFormat(FORMAT_PATTERN, Locale.CHINA).also {
                threadLocal.set(it)
            }
        }
    }

    fun parseTime(timestamp: Long): String {
        return getSimpleDateFormat().format(timestamp)
    }
}