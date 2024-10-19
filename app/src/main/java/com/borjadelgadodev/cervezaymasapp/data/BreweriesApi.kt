package com.borjadelgadodev.cervezaymasapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface BreweriesApi {
    @GET("v1/breweries/{id}")
    suspend fun getBreweryById(@Path("id") id: String): Brewery

    @GET("v1/breweries")
    suspend fun getBreweries(): List<Brewery>
}