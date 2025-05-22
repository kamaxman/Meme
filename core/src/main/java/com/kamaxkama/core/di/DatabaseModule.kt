package com.kamaxkama.core.di

import android.content.Context
import androidx.room.Room
import com.kamaxkama.core.data.local.AppDatabase
import com.kamaxkama.core.data.local.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "meme_db").build()

    @Provides
    fun provideFavoriteDao(database: AppDatabase): FavoriteDao =
        database.favoriteDao()
}
