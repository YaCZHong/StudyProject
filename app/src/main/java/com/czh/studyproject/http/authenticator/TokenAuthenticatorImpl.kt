package com.czh.studyproject.http.authenticator

import android.text.TextUtils
import com.czh.http.authenticator.TokenAuthenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

object TokenAuthenticatorImpl : TokenAuthenticator() {

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = getCurrentToken()

        // 可以通过一个特定的接口获取新的 Token, 此处要用到同步的 Retrofit 请求
        // Call<String> call = service.refreshToken(refreshToken); // 此行为示例代码

        // 获得的新的 Token
        val newToken = "" // call.execute().body(); // 示例代码

        if (TextUtils.isEmpty(newToken)) {
            return null
        }

        return response.request.newBuilder()
            .header("token", newToken)
            .build()
    }

    /**
     * 获取当前的token
     */
    override fun getCurrentToken(): String {
        return "ABC" // 示例代码
    }
}