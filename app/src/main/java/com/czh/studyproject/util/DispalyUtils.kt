package com.czh.studyproject.util

import android.util.TypedValue
import com.czh.studyproject.App

fun dp2px(dp: Int): Float {
    return dp2px(dp.toFloat())
}

fun dp2px(dp: Float): Float {
    val metrics = App.sInstance.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
}

fun sp2px(sp: Int): Float {
    return sp2px(sp.toFloat())
}

fun sp2px(sp: Float): Float {
    val metrics = App.sInstance.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics)
}