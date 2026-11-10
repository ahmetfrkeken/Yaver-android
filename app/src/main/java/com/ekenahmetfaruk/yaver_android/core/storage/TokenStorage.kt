package com.ekenahmetfaruk.yaver_android.core.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.sessionDataStorage by preferencesDataStore(name = "session")

@Singleton
class TokenStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val tokenKey = stringPreferencesKey("auth_token")

    val token: Flow<String?> = context.sessionDataStorage.data.map { it[tokenKey] }

    suspend fun saveToken(token: String) {
        context.sessionDataStorage.edit { it[tokenKey] = token }
    }

    suspend fun clear() {
        context.sessionDataStorage.edit { it.remove(tokenKey) }
    }
}