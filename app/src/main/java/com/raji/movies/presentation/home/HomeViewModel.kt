package com.raji.movies.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raji.movies.data.remote.network.onError
import com.raji.movies.data.remote.network.onSuccess
import com.raji.movies.domain.MovieRespository
import com.raji.movies.presentation.util.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle, private val repository: MovieRespository
) : ViewModel() {


    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.onStart {
        fetchNowPlaying()
        fetchPopular()
        fetchUpcoming()
    }.stateIn(
        viewModelScope, started = SharingStarted.WhileSubscribed(5000L), initialValue = _state.value
    )

    private var observeNowPlayingJob: Job? = null
    private var observePopularJob: Job? = null
    private var observeUpcomingJob: Job? = null

    private fun fetchNowPlaying() {
        observeNowPlayingJob?.cancel()
        observeNowPlayingJob = viewModelScope.launch {
            _state.update {
                it.copy(isNowPlayingLoading = true)
            }
            repository.fetchNowPlayingMovies().onSuccess { result ->
                _state.update {
                    it.copy(
                        nowPlayingMovies = result, isNowPlayingLoading = false, errorMessage = null
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        nowPlayingMovies = emptyList(),
                        isNowPlayingLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
        }
    }

    private fun fetchPopular() {
        observePopularJob?.cancel()
        observePopularJob = viewModelScope.launch {
            _state.update {
                it.copy(isNowPlayingLoading = true)
            }
            repository.fetchPopularMovies().onSuccess { result ->
                _state.update {
                    it.copy(
                        popularMovies = result, isNowPlayingLoading = false, errorMessage = null
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        popularMovies = emptyList(),
                        isNowPlayingLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
        }
    }

    private fun fetchUpcoming() {
        observeUpcomingJob?.cancel()
        observeUpcomingJob = viewModelScope.launch {
            _state.update {
                it.copy(isNowPlayingLoading = true)
            }
            repository.fetchUpcomingMovies().onSuccess { result ->
                _state.update {
                    it.copy(
                        upcomingMovies = result, isNowPlayingLoading = false, errorMessage = null
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        upcomingMovies = emptyList(),
                        isNowPlayingLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
        }
    }
}