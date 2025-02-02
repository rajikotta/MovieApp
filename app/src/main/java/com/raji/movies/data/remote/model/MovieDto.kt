package com.raji.movies.data.remote.model

import com.google.gson.annotations.SerializedName
import com.raji.movies.domain.Genre
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
import com.raji.movies.domain.IMG_BASE_URL
import com.raji.movies.domain.Movie
import java.util.Locale

data class MovieDto(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)


fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        rating = String.format(Locale.getDefault(), "%.1f", voteAverage),
        totalRating = voteCount,
        genres = genreIds.toGenreEnums(),
        posterUrl = "$IMG_BASE_URL$posterPath"
    )
}

fun List<Int>.toGenreEnums(): List<Genre> {
    return this
        .map {
            when (it) {
                28 -> ACTION
                12 -> ADVENTURE
                16 -> ANIMATION
                35 -> COMEDY
                80 -> CRIME
                99 -> DOCUMENTARY
                18 -> DRAMA
                10751 -> FAMILY
                14 -> FANTASY
                36 -> HISTORY
                27 -> HORROR
                10402 -> MUSIC
                9648 -> MYSTERY
                10749 -> ROMANCE
                878 -> SCIENCE_FICTION
                10770 -> TV_MOVIE
                53 -> THRILLER
                10752 -> WAR
                else -> WESTERN
            }
        }
}