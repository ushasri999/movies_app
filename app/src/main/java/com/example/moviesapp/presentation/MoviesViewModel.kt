package com.example.moviesapp.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.presentation.IMoviesViewModel
import com.example.moviesapp.data.repositories.api.RetrofitInstance
import com.example.moviesapp.data.repositories.api.TmdbApiService
import com.example.moviesapp.data.repositories.IMoviesRepository
import com.example.moviesapp.presentation.viewstate.MoviesViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MoviesViewModel(
    private val repository: IMoviesRepository
): ViewModel(), IMoviesViewModel {
    private val _viewStateFlow: MutableStateFlow<MoviesViewState> =
        MutableStateFlow(
            MoviesViewState()
        )

    override val viewStateFlow: StateFlow<MoviesViewState>
        get() = _viewStateFlow

    class Factory(
        private val repository: IMoviesRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val viewModel = MoviesViewModel(repository)
            return viewModel as T
        }
    }

    override fun dispatchViewEvent(event: MoviesViewEvent) {
        when (event) {
            MoviesViewEvent.PrintPopularMovies -> printMoviesData()
        }
    }

    fun printMoviesData() {
        viewModelScope.launch {
            try {
                val response = repository.getPopularMovies()
                Log.d("usha","response = $response");
            } catch (e: HttpException) {
                Log.e("usha", "TMDB returned HTTP ${e.code()}", e)
            } catch (e: IOException) {
                Log.e("usha", "Network request failed", e)
            }
        }
    }

}
