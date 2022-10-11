package com.maghraby.hyperonenews.data.api

import com.maghraby.hyperonenews.data.models.NewsResponseModel
import com.maghraby.hyperonenews.utils.Constants.ACCESS_KEY
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImp @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getNews(): Response<NewsResponseModel> =
        apiService.getNews(apiKey = ACCESS_KEY,"us","business")

}