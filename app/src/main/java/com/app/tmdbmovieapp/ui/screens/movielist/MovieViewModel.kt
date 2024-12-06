package com.app.tmdbmovieapp.ui.screens.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tmdbmovieapp.data.MovieRepository
import com.app.tmdbmovieapp.data.model.Movies
import com.app.tmdbmovieapp.utils.NetworkError
import com.app.tmdbmovieapp.utils.onError
import com.app.tmdbmovieapp.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _state = MutableStateFlow<MovieListState>(MovieListState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            query.debounce(500).collect {
                loadMovies(it)
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _query.value = text
    }

    fun onRetry() {
        viewModelScope.launch {
            loadMovies(query.value)
        }
    }

    private suspend fun loadMovies(query: String) {
        _state.value = MovieListState.Loading

        val result = if (query.isBlank()) movieRepository.getTrendingMovies()
        else movieRepository.searchMovie(query)

        result.onSuccess { movies ->
            _state.value = MovieListState.Success(movies)
        }.onError { error ->
            _state.value = MovieListState.Error(error)
        }
    }
}

sealed interface MovieListState {
    data object Loading : MovieListState
    data class Error(val error: NetworkError) : MovieListState
    data class Success(val movies: List<Movies>) : MovieListState
}