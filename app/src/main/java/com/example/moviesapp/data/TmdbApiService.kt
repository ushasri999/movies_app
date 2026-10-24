package com.example.moviesapp.data

import com.example.moviesapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers

interface TmdbApiService {
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    @GET("movie/popular")
    suspend fun getPopularMovies() : MovieResponse
}
