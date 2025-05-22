package com.kamaxkama.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kamaxkama.core.data.local.dao.FavoriteDao
import com.kamaxkama.core.data.local.entity.FavoriteMemeEntity

@Database(entities = [FavoriteMemeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
