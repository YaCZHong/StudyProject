package com.czh.studyproject.http.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserBean(val id: Int, val icon: String, val username: String, val email: String) : Parcelable