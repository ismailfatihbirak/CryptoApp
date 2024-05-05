package com.example.loodoscryptoapp.data.work_manager

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.viewModelScope
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.loodoscryptoapp.R
import com.example.loodoscryptoapp.domain.model.Root
import com.example.loodoscryptoapp.domain.use_case.get_assetid_crypto.GetAssetIdCryptoUseCase
import com.example.loodoscryptoapp.domain.use_case.get_fav.GetFavUseCase
import com.example.loodoscryptoapp.presentation.favorite.FavoriteState
import com.example.loodoscryptoapp.presentation.home.HomeState
import com.example.loodoscryptoapp.presentation.home.HomeViewModel
import com.example.loodoscryptoapp.util.Resource
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltWorker
class CryptoPriceCheckWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    val getFavUseCase: GetFavUseCase,
    val getAssetIdCryptoUseCase: GetAssetIdCryptoUseCase
) : Worker(appContext, params) {
    override fun doWork(): Result {
        priceCheck()
        return Result.success()
    }

    fun priceCheck() {
        val auth = Firebase.auth
        val authId = auth.currentUser?.uid ?: return
        val notificationHandler = NotificationHandler(applicationContext)
        val favState = mutableStateOf<FavState>(FavState())

        getFavUseCase.executeGetFav(authId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val cryptos = resource.data ?: emptyList()
                    favState.value = FavState(cryptos = cryptos)
                    cryptos.forEach { crypto ->
                        checkCryptoPrice(crypto, notificationHandler)
                    }
                }
                is Resource.Loading -> {
                    favState.value = FavState(isLoading = true)
                }
                is Resource.Error -> {
                    favState.value = FavState(error = resource.message ?: "Error")
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun checkCryptoPrice(root: Root, notificationHandler: NotificationHandler) {
        val assetId = root.asset_id ?: return
        getAssetIdCryptoUseCase.executeGetCrypto(assetId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    handleCryptoPrice(resource.data?.getOrNull(0), root, notificationHandler)
                }
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    fun handleCryptoPrice(cryptoData: Root?, root: Root, notificationHandler: NotificationHandler) {
        cryptoData ?: return
        val priceUsd = cryptoData.price_usd ?: return
        val priceChangePercent = (priceUsd - root.price_usd!!) / root.price_usd!! * 100
        if (priceChangePercent > 2 || priceChangePercent < -2) {
            notificationHandler.showSimpleNotification(
                cryptoData.name ?: "",
                priceChangePercent.toString()
            )
        }
    }


}


