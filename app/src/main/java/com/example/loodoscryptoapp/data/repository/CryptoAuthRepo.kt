package com.example.loodoscryptoapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CryptoAuthRepo {
    suspend fun emailAuthenticationSignUp(
        email: String,
        password: String,
        context: Context
    ): Boolean = withContext(Dispatchers.IO){
        suspendCoroutine<Boolean> { continuation ->
            val auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        continuation.resume(true)
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        continuation.resume(false)
                    }
                }
        }
    }

    suspend fun emailAuthenticationSignIn(
        email: String,
        password: String,
        context: Context
    ): Boolean = withContext(Dispatchers.IO){
        suspendCoroutine<Boolean> { continuation ->
            val auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        Log.d(ContentValues.TAG, "signInWithEmail:success")
                        continuation.resume(true)
                    } else {
                        Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        continuation.resume(false)
                    }
                }

        }
    }


}