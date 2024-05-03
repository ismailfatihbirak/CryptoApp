package com.example.loodoscryptoapp.data.repository

import com.example.loodoscryptoapp.domain.model.Root
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CryptoFavRepo {
    suspend fun saveFav(authId: String, asset_id: String, id_icon: String, name: String, price_usd: String) =
        withContext(Dispatchers.IO) {
            val fav = Root(asset_id,id_icon,name,price_usd.toDouble())
            Firebase.firestore.collection("Users").document(authId)
                .collection("FavoriteAssets").add(fav)
        }
    suspend fun getFavList(authId: String): List<Root> = suspendCoroutine { continuation ->
        val list = ArrayList<Root>()
        Firebase.firestore.collection("Users").document(authId)
            .collection("FavoriteAssets").get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val fav = document.toObject(Root::class.java)
                    fav?.let {
                        list.add(it)
                    }
                }
                continuation.resume(list)
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
    suspend fun deleteFav(authId: String, asset_id: String): Boolean =
        withContext(Dispatchers.IO) {
            val query = Firebase.firestore.collection("Users").document(authId)
                .collection("FavoriteAssets").whereEqualTo("asset_id", asset_id)
            val result = query.get().await()
            for (document in result.documents) {
                document.reference.delete()
            }
            result.documents.isEmpty()
        }



}