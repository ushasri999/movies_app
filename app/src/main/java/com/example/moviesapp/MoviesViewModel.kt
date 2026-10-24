package com.example.moviesapp

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviesapp.data.RetrofitInstance
import com.example.moviesapp.data.TmdbApiService
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


class MoviesViewModel: ViewModel(), IMoviesViewModel {
    override fun printMoviesData() {
        var apiInterface = RetrofitInstance.getInstance().create(TmdbApiService::class.java)

        viewModelScope.launch {

            try {
                val response = apiInterface.getPopularMovies()
                Log.d("usha","response = $response");
            } catch (e: HttpException) {
                Log.e("usha", "TMDB returned HTTP ${e.code()}", e)
            } catch (e: IOException) {
                Log.e("usha", "Network request failed", e)
            }
        }
    }

}
