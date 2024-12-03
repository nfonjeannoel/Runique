package com.camgist.auth.data

import com.camgist.auth.domain.AuthRepository
import com.camgist.core.data.networking.post
import com.camgist.core.domain.util.DataError
import com.camgist.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {
    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(email, password)
        )
    }
}