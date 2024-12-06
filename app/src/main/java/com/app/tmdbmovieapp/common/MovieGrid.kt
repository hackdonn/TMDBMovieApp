package com.app.tmdbmovieapp.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.tmdbmovieapp.data.model.Movies

private const val COLUMN_NUMBER = 2

@Composable
fun MovieGrid(
    items: List<Movies>,
    onMovieClicked: (Movies) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMN_NUMBER),
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = contentPadding.calculateBottomPadding(),
            start = 8.dp,
            end = 8.dp,
        )
    ) {
        items(items.size) { index ->
            MovieItem(items[index], onMovieClicked)
        }
    }
}