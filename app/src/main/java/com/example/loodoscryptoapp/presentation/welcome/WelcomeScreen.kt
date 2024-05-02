package com.example.loodoscryptoapp.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loodoscryptoapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
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

        TextButton(
            onClick = { navController.navigate("signup") }) {
            Text(
                text = "Sign Up With Email",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black)
        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 20.dp)){
            TextButton(onClick = { navController.navigate("signin") }){
                Text(
                    text = "Already have an account",
                    fontSize = 14.sp
                )
                Text(
                    text = " Sign in.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black)
            }
        }

    }

}

