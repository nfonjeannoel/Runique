package com.camgist.auth.presentation.di

import com.camgist.auth.presentation.login.LoginViewModel
import com.camgist.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}