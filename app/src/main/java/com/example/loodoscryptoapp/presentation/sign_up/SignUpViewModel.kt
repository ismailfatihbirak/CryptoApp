package com.example.loodoscryptoapp.presentation.sign_up

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.sign_up.SignUpUseCase
import com.example.loodoscryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel@Inject constructor(
    private val signUpUseCase : SignUpUseCase,
) : ViewModel() {
    private val _state = mutableStateOf<SignUpState>(SignUpState())
    val state: State<SignUpState> = _state

    private fun signUp(email:String,password:String,context: Context) {
        signUpUseCase.executeSignUp(email, password, context).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = SignUpState(auth = it.data ?: false)
                }
                is Resource.Loading -> {
                    _state.value = SignUpState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SignUpState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadSignUp(email:String,password:String,context: Context) {
        signUp(email, password, context)
    }
}