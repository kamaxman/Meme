package com.kamaxkama.meme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.runBlocking


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    fun toggleFavorite(meme: Meme) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(meme.imageUrl)
            if (isFav) {
                repository.delete(meme)
            } else {
                repository.insert(meme)
            }
        }
    }

    fun isFavorite(url: String): Boolean {
        return runBlocking {
            repository.isFavorite(url)
        }
    }
    fun delete(meme: Meme) {
        viewModelScope.launch {
            repository.delete(meme)
        }
    }


}
