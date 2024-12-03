package com.camgist.auth.data.di

import com.camgist.auth.data.AuthRepositoryImpl
import com.camgist.auth.data.EmailPatternValidator
import com.camgist.auth.domain.AuthRepository
import com.camgist.auth.domain.PatternValidator
import com.camgist.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }

    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}