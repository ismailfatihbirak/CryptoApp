package com.example.loodoscryptoapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.get_crypto.GetCryptoUseCase
import com.example.loodoscryptoapp.domain.use_case.save_fav.SaveFavUseCase
import com.example.loodoscryptoapp.presentation.home.HomeState
import com.example.loodoscryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveFavUseCase : SaveFavUseCase,
) : ViewModel(){

    private fun saveFavUser(authId:String,
                            asset_id:String,
                            id_icon:String,
                            name:String,
                            price_usd:String) {
        viewModelScope.launch {
            saveFavUseCase.executeSaveFav(authId, asset_id, id_icon, name, price_usd)
        }
    }


    fun saveFav(authId:String,
                asset_id:String,
                id_icon:String,
                name:String,
                price_usd:String) {
        saveFavUser(authId, asset_id, id_icon, name, price_usd)
    }
}