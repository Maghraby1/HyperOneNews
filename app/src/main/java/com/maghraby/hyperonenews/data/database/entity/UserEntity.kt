package com.maghraby.hyperonenews.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val name: String,
    val password: String
)
