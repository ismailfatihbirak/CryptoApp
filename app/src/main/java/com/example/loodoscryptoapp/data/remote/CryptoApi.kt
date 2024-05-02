package com.example.loodoscryptoapp.data.remote

import com.example.loodoscryptoapp.domain.model.Main
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    //https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd

    @GET("coins/markets?vs_currency=usd")
    suspend fun getCrypto(
    ): List<Main>


}