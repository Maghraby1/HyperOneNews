package com.maghraby.hyperonenews.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maghraby.hyperonenews.data.database.entity.NewsEntity
import com.maghraby.hyperonenews.data.database.entity.UserEntity

@Dao
interface dao {
    @Query(" SELECT * FROM User WHERE username = :username AND password = :password LIMIT  1")
    suspend fun getUsers( username: String, password: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(userEntity: UserEntity) : Long

    @Query("SELECT * FROM User")
    fun getUsers(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM News")
    suspend fun getNews(): List<NewsEntity>

    @Insert
    suspend fun insertNews( news: NewsEntity) : Long
}