package com.example.loodoscryptoapp.presentation.favorite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.delete_fav.DeleteFavUseCase
import com.example.loodoscryptoapp.domain.use_case.get_crypto.GetCryptoUseCase
import com.example.loodoscryptoapp.domain.use_case.get_fav.GetFavUseCase
import com.example.loodoscryptoapp.domain.use_case.save_fav.SaveFavUseCase
import com.example.loodoscryptoapp.presentation.home.HomeState
import com.example.loodoscryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val getFavUseCase: GetFavUseCase,
    private val deleteFavUseCase: DeleteFavUseCase
) : ViewModel() {

    private val _state = mutableStateOf<FavoriteState>(FavoriteState())
    val state: State<FavoriteState> = _state

    private fun getFavCrypto(authId: String) {
        getFavUseCase.executeGetFav(authId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = FavoriteState(cryptos = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }
                is Resource.Error -> {
                    val errorMessage = "${it.javaClass.simpleName}: ${it.message}"
                    Log.e("axax2", errorMessage)
                    _state.value = FavoriteState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteFavCrypto(authId:String,asset_id:String){
        deleteFavUseCase.executeDeleteFav(authId,asset_id).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = FavoriteState(delete = it.data ?: false)
                    loadGetFavCrypto(authId)
                }
                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }
                is Resource.Error -> {
                    val errorMessage = "${it.javaClass.simpleName}: ${it.message}"
                    Log.e("axax2", errorMessage)
                    _state.value = FavoriteState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadGetFavCrypto(authId: String) {
        getFavCrypto(authId)
    }
    fun deleteFav(authId:String,asset_id:String) {
        deleteFavCrypto(authId, asset_id)
    }
}