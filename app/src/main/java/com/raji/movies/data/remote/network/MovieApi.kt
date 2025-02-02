package com.raji.movies.data.remote.network

import com.raji.movies.data.remote.model.MovieListResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") token: String = "Bearer $BEARER_TOKEN",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponseDto>

    @GET("popular")
    suspend fun getPopular(
        @Header("Authorization") token: String = "Bearer $BEARER_TOKEN",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponseDto>


    @GET("upcoming")
    suspend fun getUpcoming(
        @Header("Authorization") token: String = "Bearer $BEARER_TOKEN",
        @Query("language") lang: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieListResponseDto>
}


const val BEARER_TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNmZjNmJhMDFhMjNhYmNlMDI3NGNiMGRkYTY1OThlOSIsIm5iZiI6MTY3NDY0MzgyNS4xNjUsInN1YiI6IjYzZDEwOTcxYTQxMGM4MTFmMTkyNTc1YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ILdrhntIOBQkQoR_0SuCuCvo1THOon0MvJ2VGCXPlV4"