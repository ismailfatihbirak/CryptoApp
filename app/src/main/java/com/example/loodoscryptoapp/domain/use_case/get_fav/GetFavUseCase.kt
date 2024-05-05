package com.example.loodoscryptoapp.domain.use_case.get_fav

import com.example.loodoscryptoapp.data.repository.CryptoFavRepo
import com.example.loodoscryptoapp.domain.model.Root
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetFavUseCase @Inject constructor(private val repository : CryptoFavRepo) {
    fun executeGetFav(authId: String) : Flow<Resource<List<Root>>> = flow{
        try {
            emit(Resource.Loading())
            val cryptoList = repository.getFavList(authId)
            emit(Resource.Success(cryptoList))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}