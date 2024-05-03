package com.example.loodoscryptoapp.data.remote

import com.example.loodoscryptoapp.domain.model.Root
import com.example.loodoscryptoapp.util.Constant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {
    @GET("assets")
    suspend fun getCrypto(
        @Query("apikey") apiKey :String = API_KEY
    ):List<Root>

}