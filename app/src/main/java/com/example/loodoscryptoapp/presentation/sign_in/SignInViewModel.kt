package com.example.loodoscryptoapp.presentation.sign_in

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loodoscryptoapp.domain.use_case.sign_in.SignInUseCase
import com.example.loodoscryptoapp.domain.use_case.sign_up.SignUpUseCase
import com.example.loodoscryptoapp.presentation.sign_up.SignUpState
import com.example.loodoscryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel(){

    private val _state = mutableStateOf<SignInState>(SignInState())
    val state: State<SignInState> = _state

    private fun signIn(email:String,password:String,context: Context) {
        signInUseCase.executeSignIn(email, password, context).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = SignInState(auth = it.data ?: false)
                }
                is Resource.Loading -> {
                    _state.value = SignInState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = SignInState(error = it.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadSignIn(email:String,password:String,context: Context) {
        signIn(email, password, context)
    }
}