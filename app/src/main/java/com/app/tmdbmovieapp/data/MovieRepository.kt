package com.app.tmdbmovieapp.data

import com.app.tmdbmovieapp.BuildConfig
import com.app.tmdbmovieapp.data.model.Movies
import com.app.tmdbmovieapp.data.model.MoviesResponse
import com.app.tmdbmovieapp.data.network.Endpoints
import com.app.tmdbmovieapp.utils.NetworkError
import com.app.tmdbmovieapp.utils.Result
import com.app.tmdbmovieapp.utils.createUrl
import com.app.tmdbmovieapp.utils.map
import com.app.tmdbmovieapp.utils.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

interface MovieRepository {
    suspend fun getTrendingMovies(): Result<List<Movies>, NetworkError>
    suspend fun searchMovie(query: String): Result<List<Movies>, NetworkError>
}

class MovieRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : MovieRepository {

    override suspend fun getTrendingMovies(): Result<List<Movies>, NetworkError> {
        return safeCall<MoviesResponse> {
            httpClient.get {
                url(createUrl(Endpoints.TRENDING_MOVIES))
                parameter("api_key", BuildConfig.TMDB_API_KEY)
            }
        }.map { it.results }
    }

    override suspend fun searchMovie(query: String): Result<List<Movies>, NetworkError> {
        return safeCall<MoviesResponse> {
            httpClient.get {
                url {
                    url(createUrl(Endpoints.SEARCH))
                    parameter("api_key", BuildConfig.TMDB_API_KEY)
                    parameter("include_adult", true)
                    parameter("query", query)
                }
            }
        }.map { it.results }
    }
}