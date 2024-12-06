package com.app.tmdbmovieapp.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tmdbmovieapp.ui.theme.TMDBMovieAppTheme

@Composable
fun LoadingUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(38.dp)
                .padding(bottom = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProgressIndicatorPreview() {
    TMDBMovieAppTheme {
        LoadingUI(Modifier)
    }
}