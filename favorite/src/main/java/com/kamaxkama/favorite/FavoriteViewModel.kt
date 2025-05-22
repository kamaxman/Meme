package com.kamaxkama.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoriteViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    val favorites: StateFlow<List<Meme>> = repository.getAllFavorites()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
