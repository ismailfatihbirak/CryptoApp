package com.example.loodoscryptoapp.presentation.sign_up

data class SignUpState(
    val isLoading : Boolean = false,
    val auth : Boolean = false,
    val error : String = "",
)