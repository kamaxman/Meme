package com.kamaxkama.core.data.repository

import com.kamaxkama.core.data.mapper.toDomain
import com.kamaxkama.core.data.remote.MemeApiService
import com.kamaxkama.core.domain.model.Meme
import com.kamaxkama.core.domain.repository.MemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MemeRepositoryImpl(
    private val api: MemeApiService
) : MemeRepository {
    override fun getMemes(): Flow<List<Meme>> = flow {
        val response = api.getMemes()
        emit(response.memes.map { it.toDomain() })
    }
}
