package com.maghraby.hyperonenews.data.api

import com.maghraby.hyperonenews.data.models.NewsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews( @Query("apiKey") apiKey: String,
                         @Query("country") country: String,
                         @Query("category") category: String
    ): Response<NewsResponseModel>
}