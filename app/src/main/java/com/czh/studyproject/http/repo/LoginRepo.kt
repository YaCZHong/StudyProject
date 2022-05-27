package com.czh.studyproject.http.repo

import com.czh.http.HttpManager
import com.czh.studyproject.http.api.LoginApi
import com.czh.studyproject.http.model.response.LoginResponse
import retrofit2.create

object LoginRepo {
    private val loginApi = HttpManager.retrofit.create<LoginApi>()

    suspend fun login(userName: String, password: String): LoginResponse {
        return loginApi.userLogin(userName, password)
    }
}