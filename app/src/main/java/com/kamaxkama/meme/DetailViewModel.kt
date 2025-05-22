package com.kamaxkama.meme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.usecase.DeleteFavoriteUseCase
import com.kamaxkama.core.domain.usecase.IsFavoriteUseCase
import com.kamaxkama.core.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    fun toggleFavorite(meme: Meme) {
        viewModelScope.launch {
            toggleFavoriteUseCase(meme)
        }
    }

    fun isFavorite(url: String): Boolean {
        return runBlocking {
            isFavoriteUseCase(url)
        }
    }

    fun delete(meme: Meme) {
        viewModelScope.launch {
            deleteFavoriteUseCase(meme)
        }
    }
}
