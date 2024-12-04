package com.camgist.auth.domain

import com.camgist.core.domain.util.DataError
import com.camgist.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
}