package com.example.loodoscryptoapp.presentation.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loodoscryptoapp.presentation.home.BottomNav
import com.example.loodoscryptoapp.presentation.home.CryptoItem
import com.example.loodoscryptoapp.presentation.home.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun FavoriteScreen(navController: NavController,viewModel: FavoriteViewModel= hiltViewModel()) {
    lateinit var auth: FirebaseAuth
    auth = Firebase.auth
    val currentUser = auth.currentUser!!.uid
    LaunchedEffect(true){
        viewModel.loadGetFavCrypto(currentUser)
    }
    var currentScreen by remember { mutableStateOf(Screen.FAVORITE) }
    val state by remember { mutableStateOf(viewModel.state) }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = Color.White) {
                BottomNav(onHomeClick = {
                    currentScreen = Screen.HOME
                    navController.navigate("home"){
                    popUpTo("home") {inclusive=true}
                } },
                    onFavoriteClick = {
                        currentScreen = Screen.FAVORITE
                        navController.navigate("fav"){
                        popUpTo("fav") {inclusive=true}
                    } }, currentScreen = currentScreen)
            }

        }
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ){
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn{
                    items(state.value.cryptos.size,
                        itemContent = {index->
                            val crypto = state.value.cryptos[index]
                            FavoriteCryptoItem(crypto = crypto,viewModel = viewModel,currentUser)
                        } )

                }
            }
        }
    }
    
}