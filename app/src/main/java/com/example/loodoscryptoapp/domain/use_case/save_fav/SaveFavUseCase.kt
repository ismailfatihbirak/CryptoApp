package com.example.loodoscryptoapp.domain.use_case.save_fav

import android.content.Context
import android.util.Log
import com.example.loodoscryptoapp.data.repository.CryptoAuthRepo
import com.example.loodoscryptoapp.data.repository.CryptoFavRepo
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SaveFavUseCase @Inject constructor(private val repository : CryptoFavRepo) {
    fun executeSaveFav (authId:String,
                        asset_id:String,
                        id_icon:String,
                        name:String,
                        price_usd:String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                repository.saveFav(authId, asset_id, id_icon, name, price_usd)
            }
        }catch (e : Exception){
            Log.e(e.message,e.message.toString())
        }
    }
}