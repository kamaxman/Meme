package com.kamaxkama.core.di

import com.kamaxkama.core.data.remote.MemeApiService
import com.kamaxkama.core.data.repository.MemeRepositoryImpl
import com.kamaxkama.core.domain.repository.MemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kamaxkama.core.data.local.dao.FavoriteDao
import com.kamaxkama.core.domain.repository.FavoriteRepository
import com.kamaxkama.core.data.repository.FavoriteRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMemeRepository(apiService: MemeApiService): MemeRepository =
        MemeRepositoryImpl(apiService)
    @Provides
    fun provideFavoriteRepository(
        dao: FavoriteDao
    ): FavoriteRepository = FavoriteRepositoryImpl(dao)

}
