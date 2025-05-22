package com.kamaxkama.core.di

import com.kamaxkama.core.domain.repository.FavoriteRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteEntryPoint {
    fun favoriteRepository(): FavoriteRepository
}
