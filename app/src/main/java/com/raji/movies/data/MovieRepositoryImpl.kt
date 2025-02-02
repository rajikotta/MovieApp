package com.raji.movies.data

import com.raji.movies.data.remote.model.toMovie
import com.raji.movies.data.remote.network.DataError
import com.raji.movies.data.remote.network.MovieApi
import com.raji.movies.data.remote.network.Result
import com.raji.movies.data.remote.network.map
import com.raji.movies.data.remote.network.performRequest
import com.raji.movies.domain.Movie
import com.raji.movies.domain.MovieRespository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : MovieRespository {


    override suspend fun fetchNowPlayingMovies(): Result<List<Movie>, DataError> {

        val response = performRequest { movieApi.getNowPlaying() }
        val domainModel = response.map { dto ->
            dto.results.map { movieDto -> movieDto.toMovie() }
        }
        return domainModel
    }

    override suspend fun fetchPopularMovies(): Result<List<Movie>, DataError> {
        val response = performRequest { movieApi.getPopular() }
        val domainModel = response.map { dto ->
            dto.results.map { movieDto -> movieDto.toMovie() }
        }
        return domainModel
    }

    override suspend fun fetchUpcomingMovies(): Result<List<Movie>, DataError> {
        val response = performRequest { movieApi.getUpcoming() }
        val domainModel = response.map { dto ->
            dto.results.map { movieDto -> movieDto.toMovie() }
        }
        return domainModel
    }


}