package com.example.moviesapp.data.entities

import com.example.moviesapp.data.entities.Result

data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
