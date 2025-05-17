package com.kamaxkama.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meme(
    val title: String,
    val imageUrl: String,
    val author: String,
    val postUrl: String,
    val subreddit: String
) : Parcelable

