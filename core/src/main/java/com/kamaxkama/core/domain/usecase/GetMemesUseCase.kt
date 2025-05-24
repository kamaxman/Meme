package com.kamaxkama.core.domain.usecase

import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.MemeRepository
import kotlinx.coroutines.flow.Flow

class GetMemesUseCase(
    private val repository: MemeRepository
) {
    operator fun invoke(): Flow<List<Meme>> = repository.getMemes()
}

