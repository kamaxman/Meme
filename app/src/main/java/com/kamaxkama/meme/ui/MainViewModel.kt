package com.kamaxkama.meme.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.usecase.GetMemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMemesUseCase: GetMemesUseCase
) : ViewModel() {

    private val _memes = MutableStateFlow<List<Meme>>(emptyList())
    val memes: StateFlow<List<Meme>> = _memes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchMemes()
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            _isLoading.value = true
            getMemesUseCase()
                .catch { e -> _error.value = e.message }
                .collect { memes ->
                    _memes.value = memes
                    _isLoading.value = false
                }
        }
    }
}
