package com.kamaxkama.core.data.local.dao

import androidx.room.*
import com.kamaxkama.core.data.local.entity.FavoriteMemeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_meme")
    fun getAllFavorites(): Flow<List<FavoriteMemeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meme: FavoriteMemeEntity)

    @Delete
    suspend fun delete(meme: FavoriteMemeEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_meme WHERE url = :url)")
    suspend fun isFavorite(url: String): Boolean
}
