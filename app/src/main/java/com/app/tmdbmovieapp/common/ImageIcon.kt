package com.app.tmdbmovieapp.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.app.tmdbmovieapp.R
import coil3.compose.AsyncImage as CoilImage

@Composable
fun ImageIcon(url: String?, contentDescription: String?, modifier: Modifier = Modifier) {
    CoilImage(
        model = url,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        placeholder = painterResource(R.drawable.ic_loading),
        error = painterResource(R.drawable.ic_error),
    )
}

@Preview(showBackground = true)
@Composable
fun ImageIconPreview() {
    ImageIcon(contentDescription = null, url = null)
}