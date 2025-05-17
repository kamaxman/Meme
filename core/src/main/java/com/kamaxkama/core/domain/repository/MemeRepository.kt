package com.kamaxkama.core.domain.repository

import com.kamaxkama.core.domain.model.Meme
import kotlinx.coroutines.flow.Flow

interface MemeRepository {
    fun getMemes(): Flow<List<Meme>>
}
