package com.camgist.auth.presentation.login

import com.camgist.core.presentation.ui.UiText

sealed interface LoginEvent {
    data object LoginSuccess : LoginEvent
    data class Error(val error: UiText): LoginEvent
}