package com.example.loodoscryptoapp.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.get_crypto.GetCryptoUseCase
import com.example.loodoscryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    private val getCryptoUseCase : GetCryptoUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState())
    val state: State<HomeState> = _state

    private fun getCrypto() {
        getCryptoUseCase.executeGetCrypto().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(cryptos = it.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                }
                is Resource.Error -> {
                    val errorMessage = "${it.javaClass.simpleName}: ${it.message}"
                    Log.e("axax2", errorMessage)
                    _state.value = HomeState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadGetCrypto() {
        getCrypto()
    }
}