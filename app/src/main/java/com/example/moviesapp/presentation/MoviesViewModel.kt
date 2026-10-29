package com.example.moviesapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.repositories.IMoviesRepository
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.presentation.viewstate.MoviesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MoviesViewModel(
    private val repository: IMoviesRepository
): ViewModel(), IMoviesViewModel {
    private val _viewStateFlow: MutableStateFlow<MoviesViewState> =
        MutableStateFlow(
            MoviesViewState(
                movies = emptyList()
            )
        )

    override val viewStateFlow: StateFlow<MoviesViewState>
        get() = _viewStateFlow

    init {
        dispatchMoviesViewEvent(MoviesViewEvent.FetchMovies)
    }

    class Factory(
        private val repository: IMoviesRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val viewModel = MoviesViewModel(repository)
            return viewModel as T
        }
    }

    override fun dispatchMoviesViewEvent(event: MoviesViewEvent) {
        val updatedState = viewStateFlow.value.run {
            when (event) {
                MoviesViewEvent.FetchMovies -> fetchMovies()
            }
        }

        _viewStateFlow.update { updatedState }
    }


    fun MoviesViewState.fetchMovies(): MoviesViewState {
        viewModelScope.launch {
            try {
                val movies = repository.getPopularMovies()
                Log.d("usha", "movies = $movies")

                _viewStateFlow.update {
                    it.copy(
                        isLoading = false,
                        movies = movies
                    )
                }

            } catch (e: IOException) {
                Log.e("usha", "Network request failed", e)
            }
        }

        return copy(
            isLoading = true
        )
    }

}
