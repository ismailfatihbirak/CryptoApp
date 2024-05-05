package com.example.loodoscryptoapp.domain.use_case.save_fav

import android.util.Log
import com.example.loodoscryptoapp.data.repository.CryptoFavRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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