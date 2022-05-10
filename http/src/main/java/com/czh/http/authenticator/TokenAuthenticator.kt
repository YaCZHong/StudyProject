package com.czh.http.authenticator

import okhttp3.Authenticator

abstract class TokenAuthenticator : Authenticator {

    abstract fun getCurrentToken(): String
}