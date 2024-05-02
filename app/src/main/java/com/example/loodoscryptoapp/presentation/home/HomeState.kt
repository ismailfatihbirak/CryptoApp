package com.example.loodoscryptoapp.presentation.home

import com.example.loodoscryptoapp.domain.model.Main

data class HomeState(
    val isLoading : Boolean = false,
    val cryptos : List<Main> = listOf(),
    val error : String = "",
)