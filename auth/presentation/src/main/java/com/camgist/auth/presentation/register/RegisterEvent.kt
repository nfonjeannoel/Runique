package com.camgist.auth.presentation.register

import com.camgist.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data object RegistrationSuccess : RegisterEvent
    data class Error(val error: UiText): RegisterEvent
}