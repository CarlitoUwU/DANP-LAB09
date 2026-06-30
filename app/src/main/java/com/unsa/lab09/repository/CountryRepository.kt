package com.unsa.lab09.repository

import com.unsa.lab09.model.Breed
import com.unsa.lab09.network.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class BreedRepository {

    suspend fun getBreedsWithImages(): List<Breed> = coroutineScope {
        val breedsResponse = RetrofitInstance.api.getAllBreeds()
        val breedNames = breedsResponse.message.keys.toList()

        breedNames.map { breedName ->
            async {
                try {
                    val imageResponse = RetrofitInstance.api.getRandomImage(breedName)
                    Breed(name = breedName, imageUrl = imageResponse.message)
                } catch (e: Exception) {
                    null
                }
            }
        }.mapNotNull { it.await() }
    }

    suspend fun getImagesForBreed(breedName: String): List<String> {
        return try {
            RetrofitInstance.api.getRandomImages(breedName).message
        } catch (e: Exception) {
            emptyList()
        }
    }
}