package com.vis.myapplication.android.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.vis.myapplication.model.Movie

@Composable
fun MainScreenWithData(movies: List<Movie>) {
    LazyColumn(
        contentPadding = PaddingValues(1.dp)
    ) {
        movies.forEach {
            item { MovieCard(movie = it) }
        }
    }
}


