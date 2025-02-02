package com.raji.movies.di

import com.raji.movies.data.remote.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    fun providesApiService(): MovieApi {

        val okHttpClientBuilder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        okHttpClientBuilder.addInterceptor(interceptor)

        return Retrofit.Builder().client(okHttpClientBuilder.build()).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

            .build().create(MovieApi::class.java)
    }


}

const val BASE_URL = "https://api.themoviedb.org/3/movie/"