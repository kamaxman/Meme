package com.kamaxkama.core.data.remote

import com.kamaxkama.core.data.model.MemeResponseDto
import retrofit2.http.GET

interface MemeApiService {
    @GET("gimme/10")
    suspend fun getMemes(): MemeResponseDto
}
