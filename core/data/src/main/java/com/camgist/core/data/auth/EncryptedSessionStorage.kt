package com.camgist.core.data.auth

import android.content.SharedPreferences
import com.camgist.core.domain.AuthInfo
import com.camgist.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull

class EncryptedSessionStorage(
    private val sharedPreferences: SharedPreferences
) : SessionStorage {
    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null) ?: return@withContext null
            json?.let {
                Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        withContext(Dispatchers.IO) {
            if (info == null) {
                sharedPreferences.edit().remove(KEY_AUTH_INFO).commit()
                return@withContext
            }
            val json = Json.encodeToString(info.toAuthInfoSerializable())
            sharedPreferences.edit().putString(KEY_AUTH_INFO, json).commit()
        }
    }

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}
