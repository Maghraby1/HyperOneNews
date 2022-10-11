package com.maghraby.hyperonenews.data.models

data class NewModel(
    val source: Source,
    val author: String,
    val description: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
)
data class Source(
    val id: String,
    val name: String
)