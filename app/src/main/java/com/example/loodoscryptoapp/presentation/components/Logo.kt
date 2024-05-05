package com.example.loodoscryptoapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loodoscryptoapp.R

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
            )
        )
    }
}