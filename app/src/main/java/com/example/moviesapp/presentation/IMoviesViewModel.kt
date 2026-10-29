package com.example.moviesapp.presentation

import com.example.moviesapp.presentation.viewstate.MoviesViewState
import kotlinx.coroutines.flow.StateFlow

interface IMoviesViewModel {
    val viewStateFlow: StateFlow<MoviesViewState>
    fun dispatchMoviesViewEvent(event: MoviesViewEvent)
}
