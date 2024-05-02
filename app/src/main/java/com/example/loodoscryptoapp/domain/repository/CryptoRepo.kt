package com.example.loodoscryptoapp.domain.repository

import com.example.loodoscryptoapp.domain.model.Main

interface CryptoRepo {
    suspend fun getCrypto() : List<Main>
}