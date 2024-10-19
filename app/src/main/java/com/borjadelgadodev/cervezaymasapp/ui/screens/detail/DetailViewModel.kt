package com.borjadelgadodev.cervezaymasapp.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borjadelgadodev.cervezaymasapp.data.BreweriesRepository
import com.borjadelgadodev.cervezaymasapp.data.Brewery
import kotlinx.coroutines.launch

class DetailViewModel (val id: String) : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    private val repository = BreweriesRepository()

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, brewery = repository.getBreweryById(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val brewery: Brewery? = null
    )
}