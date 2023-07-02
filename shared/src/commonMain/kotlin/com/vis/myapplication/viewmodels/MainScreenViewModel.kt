package com.vis.myapplication.viewmodels

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.vis.myapplication.model.Movie
import com.vis.myapplication.repo.MovieRepository
import com.vis.myapplication.repo.MovieRepositoryImpl
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : KMMViewModel() {


    private val repository: MovieRepository = MovieRepositoryImpl()

    private val _uiState = MutableStateFlow<UiState>(viewModelScope, UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getData()
    }

    private fun getData() {

        viewModelScope.coroutineScope.launch {
            try {

                val movies = repository.getMovies("US")
                _uiState.update { UiState.Success(movies.data) }
            } catch (ex: Exception) {
                _uiState.update { UiState.Error("Api Failure") }
            }
        }
    }


    sealed interface UiState {
        data class Success(
            val movies: List<Movie>
        ) : UiState

        data class Error(
            val errorMessage: String
        ) : UiState

        object Loading : UiState
    }
}