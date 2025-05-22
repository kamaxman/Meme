package com.kamaxkama.core.domain.usecase

import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteMemesUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Meme>> = repository.getAllFavorites()
}
