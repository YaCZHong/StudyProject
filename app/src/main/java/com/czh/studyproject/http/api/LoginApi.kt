package com.czh.studyproject.http.api

import com.czh.studyproject.http.CommonKeys
import com.czh.studyproject.http.LoginUrls
import com.czh.studyproject.http.model.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @POST(LoginUrls.USER_LOGIN)
    @FormUrlEncoded
    suspend fun userLogin(
        @Field(CommonKeys.USERNAME) username: String,
        @Field(CommonKeys.PASSWORD) password: String
    ): LoginResponse
}