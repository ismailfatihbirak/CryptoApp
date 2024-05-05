package com.example.loodoscryptoapp.presentation.sign_in

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loodoscryptoapp.presentation.components.EmailTextField
import com.example.loodoscryptoapp.presentation.components.Logo
import com.example.loodoscryptoapp.presentation.components.PasswordTextField
import com.example.loodoscryptoapp.presentation.components.SignButton

@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel(),navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(value = "") }
    var showPassword by remember { mutableStateOf(value = false) }
    val context = LocalContext.current

    var emailAuthControl by remember { mutableStateOf(false) }
    emailAuthControl = viewModel.state.value.auth
    var signInCompleted by remember { mutableStateOf(false) }

    if(emailAuthControl && !signInCompleted){
        signInCompleted = true
        navController.navigate("home")
        Toast.makeText(
            context,
            "sign in successful",
            Toast.LENGTH_SHORT
        ).show()
    }

    Column (
        modifier = Modifier
            .padding(all = 30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Logo()
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            EmailTextField(
                tf = email,
                onTfChange = { newTf ->
                    email = newTf
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            PasswordTextField(
                password = password,
                onPasswordChange = { newPassword ->
                    password = newPassword
                },
                showPassword = showPassword,
                onToggleShowPassword = {
                    showPassword = !showPassword
                }
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        SignButton(
            onClick = {
                viewModel.loadSignIn(email, password, context)
            },
            text = "Log in")
    }
}