package com.app.tmdbmovieapp.di

import com.app.tmdbmovieapp.data.MovieRepository
import com.app.tmdbmovieapp.data.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}