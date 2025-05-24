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
import com.kamaxkama.core.domain.usecase.ToggleFavoriteUseCase
import com.kamaxkama.core.domain.usecase.IsFavoriteUseCase
import com.kamaxkama.core.domain.usecase.DeleteFavoriteUseCase
import com.kamaxkama.core.domain.usecase.GetFavoriteMemesUseCase
import com.kamaxkama.core.domain.usecase.GetMemesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton





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
    @Provides
    fun provideToggleFavoriteUseCase(repo: FavoriteRepository): ToggleFavoriteUseCase =
        ToggleFavoriteUseCase(repo)

    @Provides
    fun provideIsFavoriteUseCase(repo: FavoriteRepository): IsFavoriteUseCase =
        IsFavoriteUseCase(repo)

    @Provides
    fun provideDeleteFavoriteUseCase(repo: FavoriteRepository): DeleteFavoriteUseCase =
        DeleteFavoriteUseCase(repo)

    @Provides
    fun provideGetFavoriteMemesUseCase(repo: FavoriteRepository): GetFavoriteMemesUseCase =
        GetFavoriteMemesUseCase(repo)

    @Provides
    fun provideGetMemesUseCase(repo: MemeRepository): GetMemesUseCase =
        GetMemesUseCase(repo)



    @Provides
    @Singleton
    fun provideMemeApiService(): MemeApiService {
        return Retrofit.Builder()
            .baseUrl("https://meme-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApiService::class.java)
    }




}
