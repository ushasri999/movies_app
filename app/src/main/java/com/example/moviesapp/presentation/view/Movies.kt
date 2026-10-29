package com.example.moviesapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviesapp.domain.Movie
import com.example.moviesapp.presentation.IMoviesViewModel
import com.example.moviesapp.presentation.MoviesViewEvent
import com.example.moviesapp.presentation.viewstate.MoviesViewState

@Composable
fun MoviesScreen(viewModel: IMoviesViewModel) {
    val viewState by viewModel.viewStateFlow.collectAsStateWithLifecycle()
    val dispatchViewEvent = viewModel::dispatchMoviesViewEvent

    MoviesScreenInternal(viewState, dispatchViewEvent)
}

@Composable
private fun MoviesScreenInternal(
    viewState: MoviesViewState,
    dispatchViewEvent: (MoviesViewEvent) -> Unit
) {
    if(viewState.isLoading) {
        LoadingScreen()
    } else {
        MoviesGridScreen(viewState.movies, dispatchViewEvent)
    }
}

@Composable
private fun MoviesGridScreen(
    movies: List<Movie>,
    dispatchViewEvent: (MoviesViewEvent) -> Unit
) {
    Scaffold (
        topBar = { TopBar() }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(20) {
                Box (
                    modifier = Modifier
                        .border(
                            width = 3.dp,
                            color = Color.Black
                        )
                        .aspectRatio(1f)
                        .background(Color.Gray)
                ) {
                    Text("Movie")
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = Color.Gray
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(title = { Text("Movies") })
}

@Preview(device = "id:pixel_5", showBackground = true, showSystemUi = true)
@Composable
private fun MoviesScreenPreview() {
//    MoviesScreenInternal()
}
