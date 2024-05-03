package com.example.loodoscryptoapp.domain.use_case.delete_fav

import android.util.Log
import com.example.loodoscryptoapp.data.repository.CryptoFavRepo
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class DeleteFavUseCase @Inject constructor(private val repository : CryptoFavRepo) {
    fun executeDeleteFav(authId:String,asset_id:String) : Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading())
            val cryptoBoolean = repository.deleteFav(authId, asset_id)
            emit(Resource.Success(cryptoBoolean))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}