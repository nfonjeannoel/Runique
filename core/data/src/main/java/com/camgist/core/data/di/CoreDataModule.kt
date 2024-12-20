package com.camgist.core.data.di

import android.content.SharedPreferences
import com.camgist.core.data.auth.EncryptedSessionStorage
import com.camgist.core.data.networking.HttpClientFactory
import com.camgist.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }


    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}