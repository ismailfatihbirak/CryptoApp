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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

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

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onToggleShowPassword: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(text = "Password",
                fontSize = 10.sp)
        },
        visualTransformation = if (showPassword) {

            VisualTransformation.None

        } else {

            PasswordVisualTransformation()

        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(
                onClick = onToggleShowPassword
            ) {
                Icon(
                    painter = painterResource(id = if (showPassword) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24),
                    contentDescription = "hide_password"
                )
            }
        }
    )
}

@Composable
fun EmailTextField(
    tf: String,
    onTfChange: (String) -> Unit
) {
    OutlinedTextField(
        value = tf,
        onValueChange = onTfChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                text = "Email",
                fontSize = 10.sp
            )
        }
    )
}

@Composable
fun SignButton(onClick: () -> Unit,text : String) {
    Button(
        onClick = onClick,colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.welcome_color),
            contentColor = Color.White
        ), modifier = Modifier.fillMaxWidth(),) {

        Text(text = text, fontSize = 12.sp)
    }
}

@Composable
fun Logo() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.loodos_icon),
            contentDescription = "" ,
            modifier = Modifier.size(150.dp,100.dp))
        Text(text = "Crypto", fontSize = 40.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            color = colorResource(
                id = R.color.welcome_color,
            ))
    }
}