package com.unsa.lab09.model

data class BreedListResponse(
    val message: Map<String, List<String>>,
    val status: String
)

data class BreedImageResponse(
    val message: String,
    val status: String
)

data class BreedImagesListResponse(
    val message: List<String>,
    val status: String
)

data class Breed(
    val name: String,
    val imageUrl: String
)