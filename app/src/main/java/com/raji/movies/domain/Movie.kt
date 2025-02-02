package com.raji.movies.domain

import com.raji.movies.R
import com.raji.movies.domain.Genre.ACTION
import com.raji.movies.domain.Genre.ADVENTURE
import com.raji.movies.domain.Genre.ANIMATION
import com.raji.movies.domain.Genre.COMEDY
import com.raji.movies.domain.Genre.CRIME
import com.raji.movies.domain.Genre.DOCUMENTARY
import com.raji.movies.domain.Genre.DRAMA
import com.raji.movies.domain.Genre.FAMILY
import com.raji.movies.domain.Genre.FANTASY
import com.raji.movies.domain.Genre.HISTORY
import com.raji.movies.domain.Genre.HORROR
import com.raji.movies.domain.Genre.MUSIC
import com.raji.movies.domain.Genre.MYSTERY
import com.raji.movies.domain.Genre.ROMANCE
import com.raji.movies.domain.Genre.SCIENCE_FICTION
import com.raji.movies.domain.Genre.THRILLER
import com.raji.movies.domain.Genre.TV_MOVIE
import com.raji.movies.domain.Genre.WAR
import com.raji.movies.domain.Genre.WESTERN
import com.raji.movies.presentation.util.UiText

data class Movie(
    val id: Int,
    val title: String,
    val rating: String,
    val totalRating: Int,
    val genres: List<Genre>,
    val posterUrl:String
)

const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500/"

enum class Genre {
    ACTION,
    ADVENTURE,
    ANIMATION,
    COMEDY,
    CRIME,
    DOCUMENTARY,
    DRAMA,
    FAMILY,
    FANTASY,
    HISTORY,
    HORROR,
    MUSIC,
    MYSTERY,
    ROMANCE,
    SCIENCE_FICTION,
    TV_MOVIE,
    THRILLER,
    WAR,
    WESTERN

}

fun Genre.toUiText(): UiText {
    return UiText.StringResourceId(
        when (this) {
            ACTION -> R.string.action
            ADVENTURE -> R.string.adventure
            ANIMATION -> R.string.animation
            COMEDY -> R.string.comedy
            CRIME -> R.string.crime
            DOCUMENTARY -> R.string.documentary
            DRAMA -> R.string.drama
            FAMILY -> R.string.family
            FANTASY -> R.string.fantasy
            HISTORY -> R.string.history
            HORROR -> R.string.horror
            MUSIC -> R.string.music
            MYSTERY -> R.string.mystery
            ROMANCE -> R.string.romance
            SCIENCE_FICTION -> R.string.science_fiction
            TV_MOVIE -> R.string.tv_movie
            THRILLER -> R.string.thriller
            WAR -> R.string.war
            WESTERN -> R.string.western
        }
    )
}