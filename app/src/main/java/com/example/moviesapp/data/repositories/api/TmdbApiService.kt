package com.example.moviesapp.data.repositories.api

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.entities.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface TmdbApiService {
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("movie/popular")
    suspend fun getPopularMovies() : MovieResponse
}
