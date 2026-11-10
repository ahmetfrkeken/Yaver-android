package com.ekenahmetfaruk.yaver_android.feature.auth.data

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(val email: String, val password: String) {
}

@Serializable
data class LoginRequest(val email: String, val password: String) {
}

@Serializable
data class UserDto(val id: Int, val email: String)

@Serializable
data class LoginResponse(val token: String, val user: UserDto) {
}

@Serializable
data class ErrorResponse(val error: String)