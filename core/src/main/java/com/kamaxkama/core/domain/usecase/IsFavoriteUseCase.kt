package com.kamaxkama.core.domain.usecase

import com.kamaxkama.core.domain.repository.FavoriteRepository

class IsFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(url: String): Boolean {
        return repository.isFavorite(url)
    }
}
