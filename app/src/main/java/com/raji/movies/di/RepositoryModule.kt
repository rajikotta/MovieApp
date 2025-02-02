package com.raji.movies.di

import com.raji.movies.data.MovieRepositoryImpl
import com.raji.movies.domain.MovieRespository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRemoteRepository(movieRemoteRepositoryImpl: MovieRepositoryImpl): MovieRespository
}