package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.data.repositories.api.RetrofitInstance
import com.example.moviesapp.data.repositories.api.TmdbApiService
import com.example.moviesapp.data.repositories.api.MoviesRepositoryApi
import com.example.moviesapp.presentation.IMoviesViewModel
import com.example.moviesapp.presentation.MoviesViewEvent
import com.example.moviesapp.presentation.MoviesViewModel
import com.example.moviesapp.presentation.view.MoviesScreen
import com.example.moviesapp.ui.theme.MoviesAppTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: IMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val apiInterface = RetrofitInstance.getInstance().create(TmdbApiService::class.java)

        val factory = MoviesViewModel.Factory(MoviesRepositoryApi(apiInterface))
        viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]


        setContent {
            MoviesAppTheme {
                MoviesScreen(viewModel)
            }
        }
    }
}
