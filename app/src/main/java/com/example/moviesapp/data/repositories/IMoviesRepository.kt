package com.example.moviesapp.data.repositories

import com.example.moviesapp.data.entities.MovieResponse

interface IMoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
}
