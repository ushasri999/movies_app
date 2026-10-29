package com.example.moviesapp.data.repositories

import com.example.moviesapp.domain.Movie

interface IMoviesRepository {
    suspend fun getPopularMovies(): List<Movie>
}
