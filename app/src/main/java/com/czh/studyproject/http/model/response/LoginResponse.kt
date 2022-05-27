package com.czh.studyproject.http.model.response

import com.czh.http.response.BaseResponse
import com.czh.studyproject.http.model.bean.UserBean
import kotlinx.parcelize.Parcelize

@Parcelize
class LoginResponse : BaseResponse<UserBean>()