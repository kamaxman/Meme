package com.kamaxkama.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.usecase.GetFavoriteMemesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    getFavoriteMemesUseCase: GetFavoriteMemesUseCase
) : ViewModel() {

    val favorites: StateFlow<List<Meme>> = getFavoriteMemesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
