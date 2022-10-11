package com.maghraby.hyperonenews.data.models

data class NewsResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<NewModel>
)
