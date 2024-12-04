package com.camgist.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camgist.auth.presentation.R
import com.camgist.auth.presentation.components.ClickableAuthSubtitle
import com.camgist.core.presentation.ui.ObserverAsEvents
import com.camgist.presentation.designsystem.EmailIcon
import com.camgist.presentation.designsystem.RuniqueTheme
import com.camgist.presentation.designsystem.components.GradientBackground
import com.camgist.presentation.designsystem.components.RuniqueActionButton
import com.camgist.presentation.designsystem.components.RuniquePasswordTextField
import com.camgist.presentation.designsystem.components.RuniqueTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onLoginSuccess: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserverAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_LONG).show()
            }

            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(context, R.string.youre_logged_in, Toast.LENGTH_LONG).show()
                onLoginSuccess()
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is LoginAction.OnRegisterClick -> onSignUpClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {

    GradientBackground {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 32.dp)
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.hi_there),
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = stringResource(id = R.string.runique_welcome_text),
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(48.dp))

            RuniqueTextField(
                state = state.email,
                startIcon = EmailIcon,
                endIcon = null,
                hint = stringResource(id = R.string.example_email),
                title = stringResource(id = R.string.email),
                keyboardType = KeyboardType.Email,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            RuniquePasswordTextField(
                state = state.password,
                isPasswordVisible = state.isPasswordVisible,
                onTogglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibility) },
                hint = stringResource(id = R.string.password),
                title = stringResource(id = R.string.password),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(32.dp))

            RuniqueActionButton(
                text = stringResource(id = R.string.login),
                isLoading = state.isLoggingIn,
                enabled = state.canLogin && !state.isLoggingIn,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onAction(LoginAction.OnLoginClick)
                }
            )
            Spacer(modifier = Modifier.height(48.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                ClickableAuthSubtitle(
                    nonClickableTextResId = R.string.dont_have_an_account,
                    clickableTextResId = R.string.sign_up
                ) {
                    onAction(LoginAction.OnRegisterClick)
                }
            }


        }
    }
}


@Preview
@Composable
fun PreviewLoginScreen() {
    RuniqueTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}