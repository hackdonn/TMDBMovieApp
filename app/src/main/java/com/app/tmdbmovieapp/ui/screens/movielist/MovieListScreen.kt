package com.app.tmdbmovieapp.ui.screens.movielist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.tmdbmovieapp.R
import com.app.tmdbmovieapp.data.model.Movies
import com.app.tmdbmovieapp.common.EmptyUI
import com.app.tmdbmovieapp.common.MovieGrid
import com.app.tmdbmovieapp.common.LoadingUI
import com.app.tmdbmovieapp.common.TopSearchBar
import com.app.tmdbmovieapp.ui.theme.TMDBMovieAppTheme
import com.app.tmdbmovieapp.utils.toString

@Composable
fun MovieListScreen(
    searchText: String,
    state: MovieListState,
    onTextChange: (String) -> Unit,
    onMovieClicked: (Movies) -> Unit,
    onRetry: () -> Unit
) {
    Scaffold(
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = contentPadding.calculateTopPadding()),
            ) {
                TopSearchBar(searchText, onTextChange)

                when (state) {
                    is MovieListState.Error -> EmptyUI(
                        title = state.error.toString(LocalContext.current),
                        showTryAgain = true,
                        onRetry = onRetry,
                        modifier = Modifier.fillMaxSize()
                    )

                    is MovieListState.Loading -> LoadingUI(
                        modifier = Modifier.fillMaxSize()
                    )

                    is MovieListState.Success -> if (state.movies.isEmpty()) {
                        EmptyUI(
                            title = stringResource(R.string.error_no_movies_found),
                            modifier = Modifier.fillMaxSize()
                        )
                    } else MovieGrid(
                        state.movies,
                        onMovieClicked,
                        contentPadding
                    )
                }
            }
        })
}

@Composable
@Preview
fun MovieListScreenLoadingPreview() {
    TMDBMovieAppTheme {
        MovieListScreen(
            searchText = "",
            state = MovieListState.Loading,
            onTextChange = { },
            onMovieClicked = { },
            onRetry = { }
        )
    }
}