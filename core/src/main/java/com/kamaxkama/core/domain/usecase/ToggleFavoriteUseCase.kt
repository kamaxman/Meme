package com.kamaxkama.core.domain.usecase

import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.FavoriteRepository

class ToggleFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(meme: Meme): Boolean {
        val isFav = repository.isFavorite(meme.imageUrl)
        if (isFav) {
            repository.delete(meme)
        } else {
            repository.insert(meme)
        }
        return !isFav
    }
}
