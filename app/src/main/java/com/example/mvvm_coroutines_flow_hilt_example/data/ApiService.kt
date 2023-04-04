package com.example.mvvm_coroutines_flow_hilt_example.data

import retrofit2.http.GET

interface ApiService {

    @GET("API/MostPopularMovies/k_9v5jw2c1")
    suspend fun getMostPopularMovies():MovieResponse
}