package com.kamaxkama.core.data.repository

import com.kamaxkama.core.data.local.dao.FavoriteDao
import com.kamaxkama.core.data.mapper.toDomain
import com.kamaxkama.core.data.mapper.toEntity
import com.kamaxkama.core.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.kamaxkama.core.domain.model.Meme


class FavoriteRepositoryImpl(
    private val dao: FavoriteDao
) : FavoriteRepository {
    override fun getAllFavorites(): Flow<List<Meme>> =
        dao.getAllFavorites().map { list -> list.map { it.toDomain() } }

    override suspend fun insert(meme: Meme) {
        dao.insert(meme.toEntity())
    }

    override suspend fun delete(meme: Meme) {
        dao.delete(meme.toEntity())
    }

    override suspend fun isFavorite(url: String): Boolean =
        dao.isFavorite(url)
}