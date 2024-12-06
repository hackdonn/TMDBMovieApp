package com.app.tmdbmovieapp.di

import com.app.tmdbmovieapp.data.network.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClient {
        return HttpClientFactory.create(CIO.create())
    }
}