package com.example.loodoscryptoapp.domain.use_case.get_crypto

import android.util.Log
import com.example.loodoscryptoapp.domain.model.Main
import com.example.loodoscryptoapp.domain.repository.CryptoRepo
import com.example.loodoscryptoapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.pow


class GetCryptoUseCase @Inject constructor(private val repository : CryptoRepo) {
    fun executeGetCrypto() : Flow<Resource<List<Main>>> = flow{
        try {
            emit(Resource.Loading())
            val cryptoList = repository.getCrypto()
            emit(Resource.Success(cryptoList))
        }catch (e : Exception){
            val errorMessage = "${e.javaClass.simpleName}: ${e.message}\n${e.stackTrace.joinToString("\n")}"
            Log.e("axax", errorMessage)
            emit(Resource.Error(e.message.toString()))
        }
    }
}

/*class GetCryptoUseCase @Inject constructor(private val repository : CryptoRepo) {
    fun executeGetCrypto() : Flow<Resource<List<Main>>> = flow{
        var retryCount = 0
        val maxRetries = 3 // Maximum number of retries

        while (retryCount < maxRetries) {
            try {
                emit(Resource.Loading())
                val cryptoList = repository.getCrypto()
                emit(Resource.Success(cryptoList))
                return@flow // Exit the loop if successful
            } catch (e: Exception) {
                val errorMessage = "${e.javaClass.simpleName}: ${e.message}\n${e.stackTrace.joinToString("\n")}"
                Log.e("axax", errorMessage)
                if (e is HttpException && e.code() == 429) {
                    // If it's a 429 error, retry after a delay
                    val waitTime = (2.toDouble().pow(retryCount) * 1000).toLong() // Exponential backoff
                    Log.d("axax", "Retrying after $waitTime milliseconds")
                    delay(waitTime)
                    retryCount++
                } else {
                    emit(Resource.Error(e.message.toString()))
                    return@flow // Exit the loop if it's not a retryable error
                }
            }
        }
        emit(Resource.Error("Max retries exceeded"))
    }
}*/







