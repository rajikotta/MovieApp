package com.raji.movies.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.raji.movies.domain.Movie

@Composable
fun MovieListItem(modifier: Modifier = Modifier, movie: Movie) {

    Box(
        modifier = Modifier
            .width(134.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = movie.posterUrl,
            contentDescription = null,
        )
    }

}