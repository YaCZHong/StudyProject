package com.czh.studyproject.http.model.bean

import androidx.annotation.Keep

@Keep
data class UserBean(val id: Int, val icon: String, val username: String, val email: String)