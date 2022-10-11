package com.maghraby.hyperonenews.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String? ="",
    val description: String?= "",
    val title: String?= "",
    val url: String? ="",
    val urlToImage: String? ="",
    val publishedAt: String?="",
    val content: String?="",
    val sourceId: String? = "",
    val sourceName: String?=""
)
