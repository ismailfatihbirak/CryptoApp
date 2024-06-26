package com.example.loodoscryptoapp.presentation.home

import com.example.loodoscryptoapp.domain.model.Root

data class HomeState(
    val isLoading : Boolean = false,
    val cryptos : List<Root> = listOf(),
    val error : String = "",
)