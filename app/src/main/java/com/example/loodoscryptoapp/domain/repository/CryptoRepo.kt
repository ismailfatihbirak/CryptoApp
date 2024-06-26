package com.example.loodoscryptoapp.domain.repository

import com.example.loodoscryptoapp.domain.model.Root

interface CryptoRepo {
    suspend fun getCrypto() : List<Root>
    suspend fun getAssetIdCrypto(assetId:String) : List<Root>
}