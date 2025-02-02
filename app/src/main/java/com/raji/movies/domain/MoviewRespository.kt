package com.raji.movies.domain

import com.raji.movies.data.remote.network.DataError
import com.raji.movies.data.remote.network.Result

interface MovieRespository {

    suspend fun fetchNowPlayingMovies(): Result<List<Movie>, DataError>
    suspend fun fetchPopularMovies(): Result<List<Movie>, DataError>
    suspend fun fetchUpcomingMovies(): Result<List<Movie>, DataError>
}