package com.example.loodoscryptoapp.presentation.sign_in

data class SignInState (
    val isLoading : Boolean = false,
    val auth : Boolean = false,
    val error : String = "",
)