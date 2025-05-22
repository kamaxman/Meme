package com.kamaxkama.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_meme")
data class FavoriteMemeEntity(
    @PrimaryKey val url: String, // gunakan url sebagai ID unik
    val title: String,
    val author: String,
    val subreddit: String
)
