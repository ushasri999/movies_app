package com.example.moviesapp.presentation

sealed class MoviesViewEvent {
    data object FetchMovies: MoviesViewEvent()
}
