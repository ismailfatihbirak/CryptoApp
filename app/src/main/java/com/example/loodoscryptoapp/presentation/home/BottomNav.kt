package com.example.loodoscryptoapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.loodoscryptoapp.R
@Composable
fun BottomNav(
    onHomeClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    currentScreen: Screen
) {
    val homeIcon = if (currentScreen == Screen.HOME) painterResource(id = R.drawable.home_icon) else painterResource(id = R.drawable.home_icon_gray)
    val favoriteIcon = if (currentScreen == Screen.FAVORITE) painterResource(id = R.drawable.fav_icon) else painterResource(id = R.drawable.fav_icon_gray)
    val homeColor = if (currentScreen == Screen.HOME) R.color.welcome_color else R.color.icon_gray
    val favoriteColor = if (currentScreen == Screen.FAVORITE) R.color.welcome_color else R.color.icon_gray

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(onClick = onHomeClick)
        ) {
            Icon(
                painter = homeIcon,
                contentDescription = "",
                tint = colorResource(id = homeColor),
                modifier = Modifier.size(35.dp)
            )
            Text(text = "Home", color = colorResource(id = homeColor))
        }
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(onClick = onFavoriteClick)
        ) {
            Icon(
                painter = favoriteIcon,
                contentDescription = "",
                tint = colorResource(id = favoriteColor),
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Favorite", color = colorResource(id = favoriteColor))
        }
    }
}