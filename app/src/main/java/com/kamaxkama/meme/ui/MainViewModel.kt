package com.kamaxkama.meme.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.data.remote.MemeApiService
import com.kamaxkama.core.data.repository.MemeRepositoryImpl
import com.kamaxkama.core.domain.model.Meme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainViewModel : ViewModel() {

    private val _memes = MutableStateFlow<List<Meme>>(emptyList())
    val memes: StateFlow<List<Meme>> = _memes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // ✅ Retrofit instance untuk API
    private val apiService = Retrofit.Builder()
        .baseUrl("https://meme-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MemeApiService::class.java)

    // ✅ Repository dari core
    private val repository = MemeRepositoryImpl(apiService)

    init {
        fetchMemes()
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getMemes()
                .catch { e -> _error.value = e.message }
                .collect { memes ->
                    _memes.value = memes
                    _isLoading.value = false
                }
        }
    }
}
