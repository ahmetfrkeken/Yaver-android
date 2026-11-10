package com.ekenahmetfaruk.yaver_android.feature.auth.data

import com.ekenahmetfaruk.yaver_android.core.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

sealed interface AuthResult {
    data object Success: AuthResult
    data class Failure(val message: String): AuthResult
}

@Singleton
class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val tokenStorage: TokenStorage,
    private val json: Json
) {
    val isLoggedIn: Flow<Boolean> = tokenStorage.token.map { it != null }

    suspend fun login(email: String, password: String): AuthResult = safeCall {
        val response = api.login(LoginRequest(email, password))
        tokenStorage.saveToken(response.token)
    }

    suspend fun register(email: String, password: String): AuthResult = safeCall {
        api.register(RegisterRequest(email, password))
        val response = api.login(LoginRequest(email, password))
        tokenStorage.saveToken(response.token)
    }

    private suspend fun safeCall(block: suspend () -> Unit): AuthResult =
        try {
            block()
            AuthResult.Success
        } catch (e: HttpException) {
            AuthResult.Failure(parseError(e))
        } catch (e: IOException) {
            AuthResult.Failure("Sunucuya ulaşılamadı. Bağlantınızı kontrol edin.")
        }

    private fun parseError(e: HttpException): String {
        val body = e.response()?.errorBody()?.string()
        val message = body?.let {
            runCatching { json.decodeFromString<ErrorResponse>(it).error }.getOrNull()
        }
        return message ?: "Beklenmeyen bir hata oluştu (${e.code()})"
    }
}