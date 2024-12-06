package com.app.tmdbmovieapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val results: List<Movies>
)