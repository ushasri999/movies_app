package com.example.moviesapp.presentation.viewstate

import com.example.moviesapp.domain.Movie

data class MoviesViewState(
    val isLoading: Boolean = false,
    val movies: List<Movie>
)
