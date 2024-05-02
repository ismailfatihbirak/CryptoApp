package com.example.loodoscryptoapp.domain.use_case.sign_in

import android.content.Context
import com.example.loodoscryptoapp.data.repository.CryptoAuthRepo
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository : CryptoAuthRepo) {
    fun executeSignIn (email:String,password:String,context: Context) : Flow<Resource<Boolean>> = flow{
        try {
            emit(Resource.Loading())
            val auth = repository.emailAuthenticationSignIn(email, password, context)
            emit(Resource.Success(auth))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}