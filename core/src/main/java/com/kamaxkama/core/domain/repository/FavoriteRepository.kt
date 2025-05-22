package com.kamaxkama.core.domain.repository

import com.kamaxkama.core.domain.model.Meme
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun getAllFavorites(): Flow<List<Meme>>
    suspend fun insert(meme: Meme)
    suspend fun delete(meme: Meme)
    suspend fun isFavorite(url: String): Boolean
}
