package com.camgist.auth.data

import com.camgist.auth.domain.AuthRepository
import com.camgist.core.data.networking.post
import com.camgist.core.domain.AuthInfo
import com.camgist.core.domain.SessionStorage
import com.camgist.core.domain.util.DataError
import com.camgist.core.domain.util.EmptyResult
import com.camgist.core.domain.util.Result
import com.camgist.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
) : AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email, password)
        )
    }

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(email, password)
        )

        if (result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }

        return result.asEmptyDataResult()
    }
}