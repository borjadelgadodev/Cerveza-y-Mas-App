package com.borjadelgadodev.cervezaymasapp.data

import com.borjadelgadodev.cervezaymasapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BreweriesClient {
    private const val BASE_URL = Constants.BASE_URL

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val instance: BreweriesApi = retrofit.create(BreweriesApi::class.java)
}