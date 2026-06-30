package com.unsa.lab09.viewmodel

import com.unsa.lab09.model.Breed

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Breed>) : UiState()
    data class Error(val message: String) : UiState()
}