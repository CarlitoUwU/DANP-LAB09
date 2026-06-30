package com.unsa.lab09.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.lab09.model.Breed
import com.unsa.lab09.repository.BreedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BreedViewModel : ViewModel() {

    private val repository = BreedRepository()

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites = _favorites.asStateFlow()

    private val _showOnlyFavorites = MutableStateFlow(false)
    val showOnlyFavorites = _showOnlyFavorites.asStateFlow()

    private val _detailImages = MutableStateFlow<List<String>>(emptyList())
    val detailImages = _detailImages.asStateFlow()

    private val _detailLoading = MutableStateFlow(false)
    val detailLoading = _detailLoading.asStateFlow()

    private var allBreeds: List<Breed> = emptyList()

    init {
        loadBreeds()
    }

    fun loadBreeds() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val breeds = repository.getBreedsWithImages().sortedBy { it.name }
                allBreeds = breeds
                applyFilters()
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    fun toggleFavorite(breedName: String) {
        val current = _favorites.value.toMutableSet()
        if (current.contains(breedName)) current.remove(breedName) else current.add(breedName)
        _favorites.value = current
        applyFilters()
    }

    fun toggleShowOnlyFavorites() {
        _showOnlyFavorites.value = !_showOnlyFavorites.value
        applyFilters()
    }

    private fun applyFilters() {
        var result = allBreeds

        if (_searchQuery.value.isNotBlank()) {
            result = result.filter { it.name.contains(_searchQuery.value, ignoreCase = true) }
        }

        if (_showOnlyFavorites.value) {
            result = result.filter { _favorites.value.contains(it.name) }
        }

        _uiState.value = UiState.Success(result)
    }

    fun loadDetailImages(breedName: String) {
        viewModelScope.launch {
            _detailLoading.value = true
            _detailImages.value = repository.getImagesForBreed(breedName)
            _detailLoading.value = false
        }
    }
}