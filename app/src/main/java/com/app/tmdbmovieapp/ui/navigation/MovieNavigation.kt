package com.app.tmdbmovieapp.ui.navigation

import kotlinx.serialization.Serializable

sealed interface MovieNavigation {
    @Serializable
    object Movies

    @Serializable
    data class MovieDetail(
        val id: Int,
        val title: String,
        val description: String,
        val poster: String
    ) : MovieNavigation
}