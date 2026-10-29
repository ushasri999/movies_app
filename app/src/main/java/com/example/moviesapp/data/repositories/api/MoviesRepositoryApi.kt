package com.example.moviesapp.data.repositories.api

import com.example.moviesapp.data.repositories.IMoviesRepository
import com.example.moviesapp.domain.Movie

class MoviesRepositoryApi(
    private val tmdbApiService: TmdbApiService
): IMoviesRepository {
    override suspend fun getPopularMovies(): List<Movie> {
        return tmdbApiService.getPopularMovies().results.map {
            Movie(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdrop_path,
                genreIds = it.genre_ids,
                originalLanguage = it.original_language,
                originalTitle = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.poster_path,
                releaseDate = it.release_date,
                softcore = it.softcore,
                title = it.title,
                video = it.video,
                voteAverage = it.vote_average,
                voteCount = it.vote_count
            )
        }
    }
}
