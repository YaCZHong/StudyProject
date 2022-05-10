package com.czh.xhlib

import java.io.File

object AppEnvironment {
    private const val DIR_TEMP = "temp"  //临时文件应放在cache目录下，是可以被清理的文件夹
    private const val DIR_HTTP_CACHE = "httpCache"

    val filesDir: String = AppConfig.context.filesDir.absolutePath

    private val cacheDir: String = AppConfig.context.cacheDir.absolutePath
    val httpCacheDir: String = cacheDir + File.separator + DIR_HTTP_CACHE
    val tempDir: String = cacheDir + File.separator + DIR_TEMP
}
