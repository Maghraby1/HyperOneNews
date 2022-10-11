package com.maghraby.hyperonenews.data.repository

import com.maghraby.hyperonenews.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getNews() = apiHelper.getNews()
}