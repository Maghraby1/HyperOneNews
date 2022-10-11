package com.maghraby.hyperonenews.data.api

import com.maghraby.hyperonenews.data.models.NewsResponseModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getNews(): Response<NewsResponseModel>
}