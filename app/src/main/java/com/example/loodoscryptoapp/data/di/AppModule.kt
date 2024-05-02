package com.example.loodoscryptoapp.data.di

import com.example.loodoscryptoapp.data.remote.CryptoApi
import com.example.loodoscryptoapp.data.repository.CryptoAuthRepo
import com.example.loodoscryptoapp.data.repository.CryptoRepoImpl
import com.example.loodoscryptoapp.domain.repository.CryptoRepo
import com.example.loodoscryptoapp.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCryptoAuthRepository(): CryptoAuthRepo {
        return CryptoAuthRepo()
    }

    @Provides
    @Singleton
    fun provideUniversityApi() : CryptoApi {
        return Retrofit.Builder().
        baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversityRepo(api: CryptoApi) : CryptoRepo{
        return CryptoRepoImpl(api = api)
    }
}