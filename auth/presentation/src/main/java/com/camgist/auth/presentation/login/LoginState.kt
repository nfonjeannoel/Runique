package com.camgist.auth.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import com.camgist.auth.domain.PasswordValidationState

data class LoginState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val isPasswordVisible: Boolean = false,
    val isLoggingIn: Boolean = false,
    val canLogin : Boolean = false
)
