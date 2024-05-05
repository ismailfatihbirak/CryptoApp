package com.example.loodoscryptoapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.save_fav.SaveFavUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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