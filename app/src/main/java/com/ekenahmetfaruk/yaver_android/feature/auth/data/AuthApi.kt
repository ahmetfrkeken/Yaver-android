package com.ekenahmetfaruk.yaver_android.feature.auth.data

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): UserDto

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}