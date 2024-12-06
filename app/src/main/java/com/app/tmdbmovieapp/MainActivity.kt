package com.app.tmdbmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.tmdbmovieapp.data.model.Movies
import com.app.tmdbmovieapp.ui.navigation.MovieNavigation
import com.app.tmdbmovieapp.ui.screens.moviedetail.MovieDetailsScreen
import com.app.tmdbmovieapp.ui.screens.movielist.MovieListScreen
import com.app.tmdbmovieapp.ui.screens.movielist.MovieViewModel
import com.app.tmdbmovieapp.ui.theme.TMDBMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val localNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBMovieAppTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    localNavController provides navController,
                ) {
                    MainContent()
                }
            }
        }
    }

    @Composable
    fun MainContent() {
        NavHost(navController = localNavController.current, startDestination = MovieNavigation.Movies) {
            composable<MovieNavigation.Movies> {
                val viewModel = hiltViewModel<MovieViewModel>()

                val searchText by viewModel.query.collectAsStateWithLifecycle()
                val state by viewModel.state.collectAsStateWithLifecycle()

                val navController = localNavController.current
                val onMovieClicked: (Movies) -> Unit =
                    { movie ->
                        navController.navigate(
                            MovieNavigation.MovieDetail(
                                movie.id,
                                movie.title,
                                movie.overview,
                                movie.posterWithPrefix
                            )
                        )
                    }

                MovieListScreen(
                    searchText = searchText,
                    state = state,
                    onTextChange = viewModel::onSearchTextChange,
                    onMovieClicked = onMovieClicked,
                    onRetry = viewModel::onRetry
                )
            }

            composable<MovieNavigation.MovieDetail> {
                val movie = it.toRoute<MovieNavigation.MovieDetail>()
                val navController = localNavController.current

                MovieDetailsScreen(movie = movie, onBackClicked = {
                    navController.navigateUp()
                })
            }
        }
    }
}