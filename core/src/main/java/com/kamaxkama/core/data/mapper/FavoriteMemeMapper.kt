package com.kamaxkama.core.data.mapper

import com.kamaxkama.core.data.local.entity.FavoriteMemeEntity
import com.kamaxkama.core.domain.model.Meme

fun Meme.toEntity(): FavoriteMemeEntity = FavoriteMemeEntity(
    url = imageUrl,
    title = title,
    author = author,
    subreddit = subreddit
)

fun FavoriteMemeEntity.toDomain(): Meme = Meme(
    imageUrl = url,
    title = title,
    author = author,
    subreddit = subreddit,
    postUrl = url // karena kita tidak simpan postLink, pakai url
)
