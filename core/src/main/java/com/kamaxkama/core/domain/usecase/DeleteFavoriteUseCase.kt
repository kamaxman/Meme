package com.kamaxkama.core.domain.usecase

import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.FavoriteRepository

class DeleteFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(meme: Meme) {
        repository.delete(meme)
    }
}
