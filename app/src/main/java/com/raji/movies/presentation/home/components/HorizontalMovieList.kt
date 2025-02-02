package com.raji.movies.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raji.movies.domain.Movie

@Composable
fun HorizontalMovieList(movies: List<Movie>, title: String) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            items(movies) { movie ->
                MovieListItem(movie = movie)
            }
        }
    }
}