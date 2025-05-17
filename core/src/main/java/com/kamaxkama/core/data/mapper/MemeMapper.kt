package com.kamaxkama.core.data.mapper

import com.kamaxkama.core.data.model.MemeDto
import com.kamaxkama.core.domain.model.Meme

fun MemeDto.toDomain(): Meme {
    return Meme(
        title = title,
        imageUrl = url,
        author = author,
        postUrl = postLink,
        subreddit = subreddit
    )
}
