package com.app.tmdbmovieapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.tmdbmovieapp.data.model.Movies

@Composable
fun MovieItem(
    movie: Movies,
    onMovieClicked: (Movies) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(8.dp)
            .clickable {
                onMovieClicked(movie)
            }) {
        ImageIcon(
            url = movie.posterWithPrefix,
            contentDescription = movie.title,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )

        Text(
            text = movie.title,
            modifier = Modifier.padding(top = 12.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}