package com.example.loodoscryptoapp.presentation.favorite

import com.example.loodoscryptoapp.domain.model.Root

data class FavoriteState(
    val isLoading : Boolean = false,
    val cryptos : List<Root> = listOf(),
    val error : String = "",
    val delete : Boolean = false
)