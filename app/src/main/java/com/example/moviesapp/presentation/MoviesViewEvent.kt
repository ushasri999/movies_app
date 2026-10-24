package com.example.moviesapp.presentation

sealed class MoviesViewEvent {
    data object PrintPopularMovies: MoviesViewEvent()
}
