package com.czh.studyproject.test


data class StringWrapper(var data: String)

fun main() {
    var wrapper = StringWrapper("23333")
    val str1 = wrapper.data
    val str2 = str1

    wrapper = StringWrapper("32222")

    println(str1)
    println(str2)
}