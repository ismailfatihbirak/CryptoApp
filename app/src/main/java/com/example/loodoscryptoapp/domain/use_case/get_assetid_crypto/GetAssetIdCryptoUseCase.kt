package com.example.loodoscryptoapp.domain.use_case.get_assetid_crypto

import com.example.loodoscryptoapp.domain.model.Root
import com.example.loodoscryptoapp.domain.repository.CryptoRepo
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetAssetIdCryptoUseCase @Inject constructor(private val repository : CryptoRepo) {
    fun executeGetCrypto(assetId: String) : Flow<Resource<List<Root>>> = flow{
        try {
            emit(Resource.Loading())
            val cryptoList = repository.getAssetIdCrypto(assetId)
            emit(Resource.Success(cryptoList))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}