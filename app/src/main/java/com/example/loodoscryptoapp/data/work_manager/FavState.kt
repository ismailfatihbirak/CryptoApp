package com.example.loodoscryptoapp.data.work_manager

import com.example.loodoscryptoapp.domain.model.Root

data class FavState(
    val isLoading : Boolean = false,
    val cryptos : List<Root> = listOf(),
    val error : String = "",
)
