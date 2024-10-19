package com.borjadelgadodev.cervezaymasapp.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.cervezaymasapp.data.BreweriesRepository
import com.borjadelgadodev.cervezaymasapp.data.Brewery
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    private val repository = BreweriesRepository()

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            try {
                val breweries = repository.getBreweries()
                state = UiState(loading = false, breweries = breweries)
            } catch (e: Exception) {
                state = UiState(loading = false, breweries = emptyList())
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val breweries: List<Brewery> = emptyList()
    )
}