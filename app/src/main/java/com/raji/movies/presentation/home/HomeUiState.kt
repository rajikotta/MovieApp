package com.raji.movies.presentation.home

import com.raji.movies.domain.Movie
import com.raji.movies.presentation.util.UiText

data class HomeUiState(
    val nowPlayingMovies: List<Movie> = emptyList(),
    val isNowPlayingLoading: Boolean = true,
    val popularMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val errorMessage: UiText? = null
)