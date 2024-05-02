package com.example.loodoscryptoapp.data.repository

import com.example.loodoscryptoapp.data.remote.CryptoApi
import com.example.loodoscryptoapp.domain.model.Main
import com.example.loodoscryptoapp.domain.repository.CryptoRepo
import javax.inject.Inject

class CryptoRepoImpl @Inject constructor(private val api : CryptoApi)  : CryptoRepo {
    override suspend fun getCrypto(): List<Main> {
        return api.getCrypto()
    }

}