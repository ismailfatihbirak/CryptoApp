package com.example.loodoscryptoapp.presentation.home

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.loodoscryptoapp.data.work_manager.CryptoPriceCheckWorker
import com.example.loodoscryptoapp.data.work_manager.NotificationHandler
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(),navController:NavController) {
    val context = LocalContext.current
    val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    LaunchedEffect(key1 = true) {
        if (!postNotificationPermission.status.isGranted) {
            postNotificationPermission.launchPermissionRequest()
        }
    }
    LaunchedEffect(true){
        viewModel.loadGetCrypto()
    }

    val request = PeriodicWorkRequestBuilder<CryptoPriceCheckWorker>(15, TimeUnit.MINUTES)
        .setInitialDelay(15, TimeUnit.MINUTES)
        .build()

    LaunchedEffect (true){
        WorkManager.getInstance(context).enqueue(request)
    }



    var text by remember { mutableStateOf("") }
    val state = viewModel.state
    val searchList = state.value.cryptos.filter { crypto ->
        crypto.asset_id!!.contains(text, ignoreCase = true) || crypto.name!!.contains(text, ignoreCase = true)}
    var currentScreen by remember { mutableStateOf(com.example.loodoscryptoapp.presentation.home.Screen.HOME) }

    Scaffold(
        bottomBar = {
            BottomAppBar (containerColor = Color.White){
                BottomNav(onHomeClick = {
                    currentScreen = com.example.loodoscryptoapp.presentation.home.Screen.HOME
                    navController.navigate("home"){
                    popUpTo("home") {inclusive=true}
                } },
                    onFavoriteClick = {
                        currentScreen =
                            com.example.loodoscryptoapp.presentation.home.Screen.FAVORITE
                        navController.navigate("fav"){
                        popUpTo("fav") {inclusive=true}
                    }},
                    currentScreen = currentScreen)
            }

        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){
            TextField(
                value = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                label = { Text(text = "Search")},
                maxLines = 1,
                shape = RoundedCornerShape(50),
                leadingIcon = { Icon(imageVector = Icons.Default.Search,
                    contentDescription = null) },
                onValueChange ={ newText->
                    text = newText
                    if (newText.isNotBlank()){
                        text = newText
                    }
                })

            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn{
                    items(searchList){
                        CryptoItem(crypto = it,navController)
                    }
                }
            }
        }
    }
}








