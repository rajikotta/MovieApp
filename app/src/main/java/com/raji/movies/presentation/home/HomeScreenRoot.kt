package com.raji.movies.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raji.movies.R
import com.raji.movies.presentation.home.components.HorizontalMovieList
import com.raji.movies.presentation.home.components.MovieSearchBar
import com.raji.movies.presentation.home.components.NowPlayingMoviesPager

@Composable
fun HomeScreenRoot(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(state: HomeUiState) {
    val query by remember { mutableStateOf("") }
    LazyColumn(contentPadding = PaddingValues(vertical = 20.dp)) {

        item {
            MovieSearchBar()

        }
        item {

            NowPlayingMoviesPager(
                movieList = state.nowPlayingMovies, title = stringResource(R.string.now_playing)
            )
        }
        if (state.popularMovies.isNotEmpty()) item {
            HorizontalMovieList(state.popularMovies, stringResource(R.string.popular))
        }
        if (state.upcomingMovies.isNotEmpty()) item {
            HorizontalMovieList(state.upcomingMovies, stringResource(R.string.upcoming))
        }

    }
}

