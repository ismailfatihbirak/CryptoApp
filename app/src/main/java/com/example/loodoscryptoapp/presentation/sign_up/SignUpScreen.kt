package com.example.loodoscryptoapp.presentation.sign_up

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loodoscryptoapp.R
import com.example.loodoscryptoapp.presentation.components.EmailTextField
import com.example.loodoscryptoapp.presentation.components.Logo
import com.example.loodoscryptoapp.presentation.components.PasswordTextField
import com.example.loodoscryptoapp.presentation.components.SignButton

@Composable
fun SignUpScreen(viewModel:SignUpViewModel= hiltViewModel(),navController:NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(value = "") }
    var showPassword by remember { mutableStateOf(value = false) }
    val context = LocalContext.current


    var emailAuthControl by remember { mutableStateOf(false) }
    emailAuthControl = viewModel.state.value.auth
    var signInCompleted by remember { mutableStateOf(false) }


    if (emailAuthControl && !signInCompleted){
        signInCompleted = true
        navController.navigate("signin")
        Toast.makeText(
            context,
            "sign up successful",
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
        SignButton(onClick = {
            viewModel.loadSignUp(email, password, context)
        },"Sign Up")
    }

}







