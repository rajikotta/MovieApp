package com.raji.movies.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil3.compose.AsyncImage
import com.raji.movies.domain.Movie
import com.raji.movies.domain.toUiText
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun NowPlayingMoviesPager(modifier: Modifier = Modifier, movieList: List<Movie>, title: String) {
    val pagerState = rememberPagerState(pageCount = { movieList.size })
    var selectedIndex = 0
    Box(
        Modifier.padding(vertical = 20.dp),
    ) {
        if (movieList.isNotEmpty()) {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val scope = rememberCoroutineScope()


                Spacer(modifier = Modifier.height(34.dp))
                HorizontalPager(
                    state = pagerState, modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = pagerState,
                        pagerSnapDistance = PagerSnapDistance.atMost(0)
                    ),
                    pageSpacing = 16.dp
                ) { page: Int ->
                    selectedIndex = page


                    AsyncImage(

                        model = movieList[page].posterUrl,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                enabled = true,
                            ) {
                                scope.launch {
                                    pagerState.animateScrollToPage(page)
                                }
                            }
                            .graphicsLayer {
                                val pageOffSet = (
                                        (pagerState.currentPage - page) + pagerState
                                            .currentPageOffsetFraction
                                        ).absoluteValue
                                scaleY = lerp(
                                    start = 0.75f,
                                    stop = 1f,
                                    fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                                )
                            }
                            .clip(RoundedCornerShape(CornerSize(16.dp)))
                    )
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.Star, tint = Color(
                            0xFFFFFF00
                        ),
                        contentDescription = ""
                    )
                    Text(
                        text =
                        "${movieList[selectedIndex].rating}(${movieList[selectedIndex].totalRating})"
                    )
                }
                Text(
                    text = movieList[selectedIndex].title,
                    style = MaterialTheme.typography.titleMedium
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    items(movieList[selectedIndex].genres) { genre ->
                        Text(
                            text =
                            genre.toUiText().asString(LocalContext.current),
                            modifier = Modifier
                                .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp)) // Outline
                                .padding(10.dp)

                        )
                    }
                }
            }

        } else {
            CircularProgressIndicator()
        }
    }

}