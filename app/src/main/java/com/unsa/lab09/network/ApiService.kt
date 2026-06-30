package com.unsa.lab09.network

import com.unsa.lab09.model.BreedImageResponse
import com.unsa.lab09.model.BreedImagesListResponse
import com.unsa.lab09.model.BreedListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedListResponse

    @GET("breed/{breed}/images/random")
    suspend fun getRandomImage(@Path("breed") breed: String): BreedImageResponse

    @GET("breed/{breed}/images/random/8")
    suspend fun getRandomImages(@Path("breed") breed: String): BreedImagesListResponse
}