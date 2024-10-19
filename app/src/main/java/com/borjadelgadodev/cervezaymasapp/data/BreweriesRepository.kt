package com.borjadelgadodev.cervezaymasapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweriesRepository {
    suspend fun getBreweryById(id: String): Brewery {
        return withContext(Dispatchers.IO) {
            BreweriesClient.instance.getBreweryById(id)
        }
    }

    suspend fun getBreweries(): List<Brewery> {
        return withContext(Dispatchers.IO) {
            BreweriesClient.instance.getBreweries()
        }
    }
}