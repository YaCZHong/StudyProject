package com.czh.studyproject.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.*

object AppExecutor {

    val ioExecutor = ThreadPoolExecutor(
        1,
        1,
        0L,
        TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>(128),
        Executors.defaultThreadFactory(),
        ThreadPoolExecutor.AbortPolicy()
    )

    val dbExecutor = ThreadPoolExecutor(
        1,
        1,
        0L,
        TimeUnit.SECONDS,
        LinkedBlockingQueue<Runnable>(128),
        Executors.defaultThreadFactory(),
        ThreadPoolExecutor.AbortPolicy()
    )

    val mainExecutor = MainExecutor()

    class MainExecutor : Executor {

        private val mainHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                command.run()
            } else {
                mainHandler.post(command)
            }
        }
    }
}