package com.example.moviesapp.data.repositories.api

import com.example.moviesapp.data.repositories.IMoviesRepository
import com.example.moviesapp.data.entities.MovieResponse
import com.example.moviesapp.data.repositories.api.TmdbApiService

class MoviesRepositoryApi(
    private val tmdbApiService: TmdbApiService
): IMoviesRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return tmdbApiService.getPopularMovies()
    }
}
