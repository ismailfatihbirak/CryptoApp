package com.example.loodoscryptoapp.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(true) {
        viewModel.loadGetCrypto()
    }

    // Observe the state from the viewModel
    val state = viewModel.state

    // Extract cryptos from the state
    val cryptos = state.value?.cryptos ?: emptyList()

    // Check if cryptos list is not empty before accessing its elements
    val firstCryptoName = if (cryptos.isNotEmpty()) cryptos[0].name else "No crypto available"

    Text(text = firstCryptoName!!)


}